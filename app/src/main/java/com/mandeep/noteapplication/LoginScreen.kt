package com.mandeep.noteapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.mandeep.noteapplication.databinding.LoginScreenActivityBinding

class LoginScreen : AppCompatActivity()
{
    lateinit var binding:LoginScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)



    }
}