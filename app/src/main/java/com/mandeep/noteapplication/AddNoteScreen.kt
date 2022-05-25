package com.mandeep.noteapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mandeep.noteapplication.MVVM.MainRepositry
import com.mandeep.noteapplication.MVVM.MyViewModel
import com.mandeep.noteapplication.Room.Notes
import com.mandeep.noteapplication.databinding.AddNoteScreenActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteScreen : AppCompatActivity()
{
    private lateinit var binding:AddNoteScreenActivityBinding


      var bitmapp:ArrayList<Bitmap>?=null
      var imagesources:ArrayList<String>?=null
      val joinId = System.currentTimeMillis().toString()

    @Inject
    lateinit var mainRepositry:MainRepositry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNoteScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        bitmapp = ArrayList()
        imagesources = ArrayList()

        supportActionBar?.hide()

        binding.addNoteButton.setOnClickListener {

            Log.d("3f3ngw",imagesources?.size.toString())
            val title = binding.addTitle.text.toString()
            val description = binding.addDescription.text.toString()

            if( title.length > 5 && description.length > 100) {

                if (bitmapList.isNotEmpty()) {
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
                }


                CoroutineScope(Dispatchers.Main).launch {

                    mainRepositry.insertNote(Notes(title, description, joinId))

                  /*  val intent = Intent(this@AddNoteScreen, HomeScreen::class.java)
                    intent.putExtra("JOIN_ID", joinId)
                    startActivity(intent)*/
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

                CoroutineScope(Dispatchers.IO).launch {
                    it.forEach {
                        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                         bitmapList.add(bitmap)
                    }

                }
            }

        })
}
