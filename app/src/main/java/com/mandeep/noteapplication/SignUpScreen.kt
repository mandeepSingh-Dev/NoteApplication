package com.mandeep.noteapplication

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mandeep.noteapplication.databinding.SignUpScreenActivityBinding


class SignUpScreen : AppCompatActivity()
{
    private lateinit var binding: SignUpScreenActivityBinding
    var name:String =""
    lateinit var phone:String
    lateinit var email:String
    lateinit var password:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
            supportActionBar?.hide()

        binding.signupButton.setOnClickListener {

            name = binding.nameinputLayout.editText?.text.toString()
            phone = binding.phoneinputLayout.editText?.text.toString()
            email = binding.emailinputLayout.editText?.text.toString()
            password = binding.passwordinputLayout.editText?.text.toString()

            val isemail = emailValidate(email)
            val ispassword = passwordValidate(password, name)
            val isphone = phoneValidate(phone)

            if(isemail && ispassword && isphone)
            {
                Toasty.message(this,"OK")
                store_encrypt_password(password)

                startActivity(Intent(this,LoginScreen::class.java),ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                finishAfterTransition()

                }



        }
    }


    fun pp(name:String):Boolean{
        return name.equals("mandeep")
    }
    private  fun emailValidate(email:String):Boolean{

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val regex = Regex(emailPattern)

        if( !email.matches(regex) )
        {
            binding.emailinputLayout.error = "Incorrect Email!"
            return false
        }
        else{
            val emailsplited = email.split("@")

            if(emailsplited[0].length !in 4..25){
                binding.emailinputLayout.error = "Appropraite name!"
                return false
            }else{
                binding.emailinputLayout.isErrorEnabled = false
                return true
            }
        }
    }

    private fun passwordValidate(password:String, name:String):Boolean{

        val stringbuilder= StringBuilder()
        Toasty.message(this,password.isEmpty().toString())
        if(password.isEmpty())
        {
            stringbuilder.append("please enter password.")
            return false
        }
        if(!password.isEmpty())
        {
            if (password.length !in 5..15) {
                stringbuilder.append("Password length should be 5-15 characters only.\n")
            }
            if (password.equals(name)) {
                stringbuilder.append("password should not match with Name\n")
            }
            if (!password.get(0).isLowerCase()) {
                stringbuilder.append("first character should be lower case only.\n")
            }
            if (!(password.contains(Regex("^(.*?[A-Z]){2,}")))) {
                stringbuilder.append("password must contains 2 upper case letters\n")
            }
            if (!password.contains(Regex("^(.*?[0-9]){2}"))) {
                stringbuilder.append("password must contain at least 2 digits.\n")
            }
            if (!password.contains(Regex("^(.*?[$&+,:;=\\\\?@#|/'<>.^*()%!-]){1}"))) {
                stringbuilder.append("password must contain at least 1 special character.\n")
            }
        }

           stringbuilder.let {
               if(it.isNotEmpty()) {
                   if(it.equals("please enter password."))
                   {
                       binding.passwordinputLayout.error = "please enter password."
                       return false
                   }else {
                       binding.passwordinputLayout.error = stringbuilder
                       return false
                   }
               } else{
                       binding.passwordinputLayout.isErrorEnabled = false
                   return true
                   }
               }
    }

    private fun phoneValidate(phone:String):Boolean{
        if(phone.length<10 || phone.length>10)
        {
            binding.phoneinputLayout.error = "InAppropriate Phone Number\nplease enter 10 digit phone number"
          return false
        }
        else{
            binding.phoneinputLayout.isErrorEnabled = false
        return true
        }
    }

    private fun store_encrypt_password(password:String){

       val masterkey =  MasterKey.Builder(this).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
      //  val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences = EncryptedSharedPreferences.create(
           this,
            "HELLO",
            masterkey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        with (sharedPreferences.edit()) {

            this.putString(email,password)
            this.putString(phone,password)
            apply()
        }
        Log.d("30g0j3g",sharedPreferences.getString(email,"").toString())
    }

}