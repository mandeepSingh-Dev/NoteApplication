package com.mandeep.noteapplication

import android.R
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandeep.noteapplication.MVVM.*
import com.mandeep.noteapplication.databinding.HomeScreenActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeScreen : AppCompatActivity()
{
    private lateinit var binding: HomeScreenActivityBinding
    private val myViewModel:MyViewModel by viewModels()
    lateinit var adapter:MyAdapter


    @Inject
    lateinit var assistedFactory: MyViewModel2AssistedFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

    /*    val bm = BitmapFactory.decodeResource(resources, R.drawable.ic_dialog_alert)

        if(bm==null)
        {
            Log.d("39tj3g3ewg","nullllllll")
        }else{
            Log.d("39tj3g3ewg","ok")
        }*/

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

                   /*it.forEach {
                       val myViewModel2:MyViewModel2 by viewModels {
                           MyViewModel2Factory(assistedFactory,it.joinId)
                       }
                       myViewModel2.image.observe(this, Observer {
                          imageList.add(it)
                           Log.d("4gh3g",it.userId)
                       })
                   }*/
                    adapter = MyAdapter(this, it/*,imageList myViewModel, this*/)
                        binding.allNotesRecyclerView.layoutManager = LinearLayoutManager(this)
                        binding.allNotesRecyclerView.adapter = adapter

                  /*  binding.allNotesRecyclerView.layoutManager = LinearLayoutManager(this)
                    binding.allNotesRecyclerView.adapter = adapter*/
                }
            })
        }catch (e:Exception){}
    }
}