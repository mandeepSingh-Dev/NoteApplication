package com.mandeep.noteapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.mandeep.noteapplication.MVVM.MyViewModel
import com.mandeep.noteapplication.Room.Notes
import com.mandeep.noteapplication.databinding.AddNoteScreenActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class AddNoteScreen : AppCompatActivity()
{
    private lateinit var binding:AddNoteScreenActivityBinding
     var bitmaps:ArrayList<Bitmap>?=null

    val myViewModel:MyViewModel by viewModels()
    private var bitmap:Bitmap?=null
    var out :ByteArrayOutputStream?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNoteScreenActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.addNoteButton.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val description = binding.addDescription.text.toString()



            if(out!=null )
            {
                Toasty.message(this,"yes")
                myViewModel.insertNote(Notes(title,description,out?.toByteArray()))
            }
            else{
                myViewModel.insertNote(Notes(title,description))
            }

            startActivity(Intent(this,HomeScreen::class.java))
        }

        binding.addphotofloatbutton.setOnClickListener {
            launcher.launch("image/*")
            if(bitmap!=null)
            {
                out= ByteArrayOutputStream()
                bitmap?.compress(Bitmap.CompressFormat.JPEG,20,out)
            }
        }


    }

    val launcher = registerForActivityResult(ActivityResultContracts.GetMultipleContents(),
        ActivityResultCallback {
           /* if(it!=null)
            {
                 bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
            }*/
        })
}