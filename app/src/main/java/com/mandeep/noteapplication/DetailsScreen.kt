package com.mandeep.noteapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.mandeep.noteapplication.databinding.AddNoteScreenActivityBinding
import com.mandeep.noteapplication.databinding.DetailsScreenActivityBinding
import com.mandeep.noteapplication.databinding.LoginScreenActivityBinding

class DetailsScreen : AppCompatActivity()
{
    private lateinit var binding: DetailsScreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }
}