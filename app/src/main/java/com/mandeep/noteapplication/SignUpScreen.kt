package com.mandeep.noteapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        binding.signupButton.setOnClickListener {

            name = binding.nameinputLayout.editText?.text.toString()
            phone = binding.phoneinputLayout.editText?.text.toString()
            email = binding.emailinputLayout.editText?.text.toString()
            password = binding.passwordinputLayout.editText?.text.toString()

            emailValidate(email)
            passwordValidate(password, name)
            phoneValidate(phone)


        }
    }

    private  fun emailValidate(email:String){

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val regex = Regex(emailPattern)

        if( !email.matches(regex) )
        {
            binding.emailinputLayout.error = "Incorrect Email!"
        }
        else{
            val emailsplited = email.split("@")

            if(emailsplited[0].length !in 4..25){
                binding.emailinputLayout.error = "Appropraite name!"
            }else{
                binding.emailinputLayout.isErrorEnabled = false
            }
        }
    }

    private  fun passwordValidate(password:String, name:String){

        val stringbuilder= StringBuilder()
        Toasty.message(this,password.isEmpty().toString())
        if(password.isEmpty())
        {
            stringbuilder.append("please enter password.")
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
                   }else {
                       binding.passwordinputLayout.error = stringbuilder
                   }
               } else{
                       binding.passwordinputLayout.isErrorEnabled = false
                   }
               }



    }

    private fun phoneValidate(phone:String){
        if(phone.length<10 || phone.length>10)
        {
            binding.phoneinputLayout.error = "InAppropriate Phone Number\nplease enter 10 digit phone number"
        }
        else{
            binding.phoneinputLayout.isErrorEnabled = false
        }
    }

}