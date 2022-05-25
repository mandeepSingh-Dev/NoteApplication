package com.mandeep.noteapplication

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandeep.noteapplication.MVVM.MyAdapter
import com.mandeep.noteapplication.MVVM.MyViewModel
import com.mandeep.noteapplication.databinding.HomeScreenActivityBinding
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

        supportActionBar?.hide()


        binding.floatAddButton.setOnClickListener {
            startActivity(Intent(this,AddNoteScreen::class.java),ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

    }

    override fun onResume() {
        super.onResume()
        try {
            myViewModel.listNotes?.observe(this, Observer {

                if(it.isEmpty())
                {
                    binding.emptyTextView.text = "Empty List \n Add Note"
                    binding.emptyTextView.visibility = View.VISIBLE
                    binding.allNotesRecyclerView.visibility = View.GONE
                }else {
                    binding.emptyTextView.visibility = View.GONE
                    binding.allNotesRecyclerView.visibility = View.VISIBLE

                    adapter = MyAdapter(this, it, myViewModel, this)
                    binding.allNotesRecyclerView.layoutManager = LinearLayoutManager(this)
                    binding.allNotesRecyclerView.adapter = adapter
                }
            })
        }catch (e:Exception){}

    }
}