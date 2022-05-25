package com.mandeep.noteapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mandeep.noteapplication.MVVM.MyViewModel
import com.mandeep.noteapplication.databinding.DetailsScreenActivityBinding
import com.mandeep.noteapplication.MVVM.GridAdpaterr
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileInputStream

@AndroidEntryPoint
class DetailsScreen : AppCompatActivity()
{
    private lateinit var binding: DetailsScreenActivityBinding
    private val myViewModel: MyViewModel by viewModels()

    var bitmapList = ArrayList<Bitmap>()

    var arraylist:ArrayList<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.hide()


        arraylist = ArrayList()
        val join_Id = intent.getStringExtra("JOIN_ID")
        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")

        binding.detailscreenTitle.text = title
        binding.detailscreenDescription.text = description

        Log.d("sdfnndjf",join_Id.toString())
CoroutineScope(Dispatchers.IO).launch {
    join_Id?.let {
        try {

            val filedir = getExternalFilesDir(it)
            val imagesfileList = filedir?.listFiles()

            imagesfileList?.forEach {
                var ipstrm = FileInputStream(it)
                val bitmap = BitmapFactory.decodeStream(ipstrm)
                bitmapList.add(bitmap)
            }
              CoroutineScope(Dispatchers.Main).launch {
                  val adapter = GridAdpaterr(this@DetailsScreen, bitmapList)
                  binding.recyclerViewGrid.layoutManager = GridLayoutManager(this@DetailsScreen, 3)
                  binding.recyclerViewGrid.adapter = adapter
              }
        } catch (e: Exception) {
        }
    }
}
       /* join_Id?.let {
            myViewModel.readImagesbyJoinId(it).observe(this, Observer{
                Log.d("sdfnndjf",it.size.toString())

                it.forEach {
                        arraylist?.add(it.imagestring)
                    }
                Log.d("38rh3gf3",arraylist?.size.toString())
                val adapter = GridAdpaterr(this,arraylist!!)

                val gridLayoutManager = GridLayoutManager(this,3)
                val totalSize: Int = arraylist?.size!!
                gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val span: Int
                        span = totalSize % 3
                        return if (totalSize < 3) {
                            6
                        } else if (span == 0 || position <= totalSize - 1 - span) {
                            2
                        } else if (span == 1) {
                            6
                        } else {
                            3
                        }
                    }
                }

                binding.recyclerViewGrid.layoutManager = GridLayoutManager(this,3)
                binding.recyclerViewGrid.adapter = adapter

            }
            )
        }*/

        /*val imageview = ImageView(this)
        imageview.setImageResource(R.drawable.ic_launcher_background)
        imageview.layoutParams = ViewGroup.LayoutParams(100,100)
        imageview.scaleType = ImageView.ScaleType.FIT_XY

        val imageview2 = ImageView(this)
        imageview.setImageResource(R.drawable.ic_launcher_background)
        imageview.layoutParams = ViewGroup.LayoutParams(100,100)
        imageview.scaleType = ImageView.ScaleType.FIT_XY

        val arr = ArrayList<ImageView>()
        arr.add(imageview)*/
        /*arr.add(imageview2)
        arr.add(imageview2)
        arr.add(imageview2)
        arr.add(imageview2)
        arr.add(imageview2)
        arr.add(imageview2)
        arr.add(imageview2)
        arr.add(imageview2)
        arr.add(imageview2)*/




       /* binding.gridlayout.addView(imageview,2)
        binding.gridlayout.addView(imageview,3)
        binding.gridlayout.addView(imageview,4)
        binding.gridlayout.addView(imageview,5)
        binding.gridlayout.addView(imageview,6)
        binding.gridlayout.addView(imageview,7)
        binding.gridlayout.addView(imageview,8)*/




        /*    join_Id?.let {
                myViewModel.readImagesbyJoinId(join_Id).observe(this, Observer {
                    Log.d("ufbsdjfd",it.size.toString())
                    val bitmap=  ImageBitmapString.StringtoBitmap(it[0].imagestring)
                   // binding.bitmapPRACT.setImageBitmap(bitmap)
                })
            }*/
    }
}