package com.mandeep.noteapplication

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mandeep.noteapplication.databinding.LoginScreenActivityBinding

class LoginScreen : AppCompatActivity()
{
    lateinit var binding:LoginScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.hide()



        //when user is loggedIn loginscreen will never open and Direct Navigate to HomeScreen.
        val sharedPreferencess =getSharedPreferences("isLoginShrdPrf", Context.MODE_PRIVATE)
        val editor = sharedPreferencess.edit()
        if(sharedPreferencess.getBoolean("isLogin",false))
        {
            startActivity(Intent(this,HomeScreen::class.java))
            finish()
        }

        binding.loginButton.setOnClickListener {
            val emailorphone =binding.phoneLogin.editText?.text.toString()
            val password =binding.passwordLogin.editText?.text.toString()
            try {
                //Getting Encrypted password frmo EncryptedSharedPrefernce.
                val masterkey =  MasterKey.Builder(this).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

                val sharedPreferences = EncryptedSharedPreferences.create(
                    this,
                    getString(R.string.EncryptShrdPref),
                    masterkey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
                val encryptedpassword = sharedPreferences.getString(emailorphone,"").toString()
                 if(encryptedpassword.isNotEmpty())
                 {
                     binding.phoneLogin.isErrorEnabled = false
                     if(encryptedpassword.equals(password))
                     {
                         binding.passwordLogin.isErrorEnabled = false

                         editor.putBoolean("isLogin",true)
                         editor.apply()

                         startActivity(Intent(this,HomeScreen::class.java))
                         finish()
                     }else{
                         binding.passwordLogin.error = "Incorrect password!"

                     }
                 }
                else{
                    binding.phoneLogin.error = "Incorrect phone/email!"
                     binding.signuupButton.visibility = View.VISIBLE
                }
            }catch (e:Exception){}
        }

        binding.signuupButton.setOnClickListener {
            startActivity(Intent(this,SignUpScreen::class.java),ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
          finish()
        }


    }

}