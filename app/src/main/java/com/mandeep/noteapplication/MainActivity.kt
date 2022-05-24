package com.mandeep.noteapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.mandeep.noteapplication.databinding.ActivityMainBinding
import com.mandeep.noteapplication.databinding.DetailsScreenActivityBinding
import com.mandeep.noteapplication.databinding.LoginScreenActivityBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)    }

}