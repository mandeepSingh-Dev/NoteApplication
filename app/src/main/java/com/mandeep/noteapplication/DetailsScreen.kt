package com.mandeep.noteapplication

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mandeep.noteapplication.MVVM.*
import com.mandeep.noteapplication.databinding.DetailsScreenActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsScreen : AppCompatActivity()
{
    private lateinit var binding: DetailsScreenActivityBinding
   // private val myViewModel: MyViewModel by viewModels()

    var bitmapList = ArrayList<Bitmap>()

    var arraylist:ArrayList<String>?=null

    @Inject
    lateinit var assistedFactory:MyViewModel2AssistedFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.hide()

        //set emoji
        val unicode = 0x270F
        val emoji = String(Character.toChars(unicode))
        binding.headlineNoteDetail.text = getString(R.string.note)+" "+emoji

        arraylist = ArrayList()
        val join_Id = intent.getStringExtra("JOIN_ID")
        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")

        binding.detailscreenTitle.text = title
        binding.detailscreenDescription.text = description

        val myViewModel2:MyViewModel2 by viewModels {
            join_Id?.let { MyViewModel2Factory(assistedFactory, it) }!!
        }

     myViewModel2.image.observe(this, Observer {

         binding.progressbar.isEnabled = true
         binding.progressbar.visibility = View.VISIBLE

         it.forEach {
            bitmapList.add(it.image)
        }
         binding.progressbar.isEnabled = false
         binding.progressbar.visibility = View.GONE

    val adapter = GridAdpaterr(this,bitmapList)
    Log.d("eifnwe",bitmapList.size.toString())
    if(bitmapList.size==1)
    {
        binding.recyclerViewGrid.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewGrid.adapter = adapter
    }else{
        binding.recyclerViewGrid.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.recyclerViewGrid.adapter = adapter
    }

})
        /* myViewModel.listImages?.observe(this, Observer {

             it.forEach {
                 bitmapList.add(it.image)
             }
             val adapter = GridAdpaterr(this,bitmapList)
             binding.recyclerViewGrid.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
             binding.recyclerViewGrid.adapter = adapter
         })*/

        Log.d("sdfnndjf",join_Id.toString())

/*
CoroutineScope(Dispatchers.IO).launch {
    join_Id?.let {
        try {
            val filedir = getExternalFilesDir(it)
            val imagesfileList = filedir?.listFiles()

            if(imagesfileList?.isNotEmpty()!!){
                withContext(Dispatchers.Main) {
                    binding.detailProgressBar.visibility = View.VISIBLE
                }
            imagesfileList.forEach {
                var ipstrm = FileInputStream(it)
                val bitmap = BitmapFactory.decodeStream(ipstrm)
                bitmapList.add(bitmap)
            }
              CoroutineScope(Dispatchers.Main).launch {
                  binding.detailProgressBar.visibility = View.GONE
                  val adapter = GridAdpaterr(this@DetailsScreen, bitmapList)
                  binding.recyclerViewGrid.setHasFixedSize(true)
                  binding.recyclerViewGrid.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
                  binding.recyclerViewGrid.adapter = adapter
              }
            }
            else{
                binding.detailProgressBar.visibility = View.GONE
            }
        } catch (e: Exception) {
        }
    }
}
*/

    }
}