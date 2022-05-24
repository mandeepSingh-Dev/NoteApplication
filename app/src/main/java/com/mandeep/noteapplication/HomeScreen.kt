package com.mandeep.noteapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandeep.noteapplication.MVVM.MyAdapter
import com.mandeep.noteapplication.MVVM.MyViewModel
import com.mandeep.noteapplication.databinding.DetailsScreenActivityBinding
import com.mandeep.noteapplication.databinding.HomeScreenActivityBinding
import com.mandeep.noteapplication.databinding.LoginScreenActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : AppCompatActivity()
{
    private lateinit var binding: HomeScreenActivityBinding
    private val myViewModel:MyViewModel by viewModels()
    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        myViewModel.listNotes?.observe(this, Observer {
            adapter = MyAdapter(this,it)
            binding.allNotesRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.allNotesRecyclerView.adapter = adapter

        })
    }
}