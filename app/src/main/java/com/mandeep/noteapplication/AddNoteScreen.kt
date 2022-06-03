package com.mandeep.noteapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mandeep.noteapplication.MVVM.GridAdpaterr
import com.mandeep.noteapplication.MVVM.MainRepositry
import com.mandeep.noteapplication.MVVM.MyViewModel
import com.mandeep.noteapplication.Room.Images
import com.mandeep.noteapplication.Room.Notes
import com.mandeep.noteapplication.databinding.AddNoteScreenActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteScreen : AppCompatActivity()
{
    private lateinit var binding:AddNoteScreenActivityBinding


      var bitmapp:ArrayList<Bitmap>?=null
      var imagesources:ArrayList<String>?=null
      val joinId = System.currentTimeMillis().toString()

       var singleBitmap:Bitmap?=null


    @Inject
    lateinit var mainRepositry:MainRepositry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNoteScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        bitmapp = ArrayList()
        imagesources = ArrayList()

        supportActionBar?.hide()



       /* myViewModel.listImages?.observe(this, Observer {

            it.forEach {
                bitmapList.add(it.image)
            }
            val adapter = GridAdpaterr(this@AddNoteScreen,bitmapList )
            binding.recyclerViewGrid2.layoutManager = GridLayoutManager(this@AddNoteScreen, 3)
            binding.recyclerViewGrid2.adapter = adapter
        })*/



        binding.addNoteButton.setOnClickListener {

            Log.d("3f3ngw",imagesources?.size.toString())
            val title = binding.addTitle.text.toString()
            val description = binding.addDescription.text.toString()

            CoroutineScope(Dispatchers.Main).launch{

                /*val intent = Intent(this@AddNoteScreen, HomeScreen::class.java)
                //intent.putExtra("JOIN_ID", joinId)
                startActivity(intent)
                        finishAfterTransition()*/
            }


            if( title.length > 5 && description.length > 100) {

                /*if (bitmapList.isNotEmpty()) {
                    val dir = getExternalFilesDir(joinId)
                    if (!dir?.exists()!!) {
                        dir.mkdir()
                    }
                    bitmapList.forEach {
                        CoroutineScope(Dispatchers.IO).launch {
                            var file = File(dir, System.currentTimeMillis().toString() + ".jpg")
                            val outptstrm = FileOutputStream(file)
                            it.compress(Bitmap.CompressFormat.JPEG, 100, outptstrm)
                        }

                    }
                }*/


                CoroutineScope(Dispatchers.Main).launch {

                  //join Id is basically worked as Foreign Key for both Notes and Images table
                    singleBitmap?.let {
                        mainRepositry.insertNote(Notes(title, description, joinId,it))
                    }
                    bitmapList.forEach {
                        mainRepositry.insertImage(Images(joinId,it))
                    }
                    val intent = Intent(this@AddNoteScreen, HomeScreen::class.java)
                    intent.putExtra("JOIN_ID", joinId)
                    startActivity(intent)
                    finishAfterTransition()
                }
            }
            else{
                if(title.length<5)
                binding.addTitle.error = "Title length should greater than 5!"

                if(description.length<100)
                    binding.addDescription.error = "Description length should greater than 100!"

            }
        }

        binding.addphotofloatbutton.setOnClickListener {
            launcher.launch("image/*")
        }


    }


    val bitmapList = ArrayList<Bitmap>()
    private val launcher = registerForActivityResult(ActivityResultContracts.GetMultipleContents(),
        ActivityResultCallback {

            if(it.isNotEmpty()) {
                try {
                    CoroutineScope(Dispatchers.IO).launch {
                        if(it.size<10)
                        {
                            //this bitmap is just for put one bitmap image to
                                //set in Notes table so that viewmodel can easily fetch
                                    //single bitmap from Notes Table rathen list of bitmap list
                                        //from Images Table
                            singleBitmap = reducesizeofBitmap(it[0])
                            //to set bitmap list(upto 10 images) for single user in Images Table
                            it.forEach {
                                val bitmapD =reducesizeofBitmap(it)
                                bitmapD?.let {
                                    bitmapList.add(it)
                                }
                            }
                        }
                        else {
                            for (i in 0..9) {

                                // it.forEach {
                                /* val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                            val options = BitmapFactory.Options()
                            options.inJustDecodeBounds = true
                            BitmapFactory.decodeStream(contentResolver.openInputStream(it),null,options)
                            Log.d("8h3g3",options.outHeight.toString())
                            Log.d("8h3g3",options.outWidth.toString())

                            options.inSampleSize = 5
                            options.inJustDecodeBounds = false
                          val bitmaprr =  BitmapFactory.decodeStream(contentResolver.openInputStream(it),null,options)
                            Log.d("8h3g3",options.outHeight.toString())
                            Log.d("8h3g3",options.outWidth.toString())
                            Log.d("8h3g3",options.inBitmap.toString()+"NULL")

                            bitmaprr?.let{
                                bitmapList.add(it)
                               Log.d("eorjeor",bitmaprr.byteCount.toString())
                                Log.d("eorjeor",bitmap.byteCount.toString())

                            }*/
                                //   val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))

                                val bitmapD = reducesizeofBitmap(it.get(i))
                                bitmapD?.let {
                                    bitmapList.add(it)
                                }
                            }
                        }
                        withContext(Dispatchers.Main){
                            val adapter = GridAdpaterr(this@AddNoteScreen, bitmapList)
                            binding.recyclerViewGrid2.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                            binding.recyclerViewGrid2.adapter = adapter
                        }
                      /*  withContext(Dispatchers.Main) {
                            val adapter = GridAdpaterr(this@AddNoteScreen, bitmapList)
                            binding.recyclerViewGrid2.layoutManager = GridLayoutManager(this@AddNoteScreen, 3)
                            binding.recyclerViewGrid2.adapter = adapter

                        }*/
                    }

                }catch (e:Exception){}

            }

        })


    // val myViewModel: MyViewModel by viewModels()

    fun reducesizeofBitmap(uri: Uri): Bitmap? {
        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
        Log.d("ef38fhn3",bitmap?.allocationByteCount.toString()+"  withoutencode")
        Log.d("fieinfe",bitmap.height.toString()+" ORIGINAL")
        Log.d("fkndfd",bitmap.width.toString()+"+ORIGINAL")


        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        BitmapFactory.decodeStream(contentResolver.openInputStream(uri),null,options)

        val imageHeight: Int = options.outHeight
        val imageWidth: Int = options.outWidth
        Log.d("49gtj4g",imageWidth.toString())
        Log.d("49gtj4g",imageHeight.toString())
//        val imageType: String = options.outMimeType

        val sampleSize = calculateInSampleSize(options,150,150)

        options.inSampleSize = sampleSize - 2
        options.inJustDecodeBounds = false

        var bitmapdecoded = BitmapFactory.decodeStream(contentResolver.openInputStream(uri),null,options)


            Log.d("ef38fhn3",bitmapdecoded?.byteCount.toString()+"  withencode")
            Log.d("fieinfe",bitmapdecoded?.height.toString())
            Log.d("fkndfd",bitmapdecoded?.width.toString())

         // val bitmappp=  Bitmap.createScaledBitmap(bitmapdecoded!!,imageWidth,imageHeight,false)
        /*Log.d("ef38fhn3",bitmappp?.byteCount.toString()+"  scaledBitmap")
        Log.d("fieinfe",bitmappp?.height.toString()+"scaledBitmap")
        Log.d("fkndfd",bitmappp?.width.toString()+"scaledBitmap")*/

     return bitmapdecoded
    }
    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
       Log.d("398tht3g",inSampleSize.toString())
        return inSampleSize
    }


}
