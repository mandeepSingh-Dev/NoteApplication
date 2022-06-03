package com.mandeep.noteapplication.MVVM

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.noteapplication.DetailsScreen
import com.mandeep.noteapplication.HomeScreen
import com.mandeep.noteapplication.R
import com.mandeep.noteapplication.Room.Images
import com.mandeep.noteapplication.Room.Notes
import java.io.FileInputStream

class MyAdapter(val context: Context, val noteList:List<Notes>,val imageList: ArrayList<Images>/*val myViewModel: MyViewModel,val activity: HomeScreen*/): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = noteList.get(position)
        //val bitmap = imageList.
        holder.titleTextView.text = note.title
        holder.descriptionTextView.text = note.description


       // CoroutineScope(Dispatchers.IO).launch {
        try {
//            val filedir = context.getExternalFilesDir(note.joinId)
//
//            var ipstrm = FileInputStream(filedir?.listFiles()?.get(0))
//            val bitmap = BitmapFactory.decodeStream(ipstrm)

            holder.imageview.setImageBitmap(note.singleBitmap)
        }catch (e:Exception){}


        holder.itemView.setOnClickListener {
            val intent = Intent(context,  DetailsScreen::class.java)
            intent.putExtra("JOIN_ID",note.joinId)
            intent.putExtra("TITLE",note.title)
            intent.putExtra("DESCRIPTION",note.description)

            context.startActivity(intent)
        }



        //val byteArray = note.byte
       /* if(byteArray!=null) {
            holder.imageview.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))
        }*/
    }

    override fun getItemCount(): Int {
       return noteList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var imageview:ImageView = itemView.findViewById(R.id.photoImagview)
        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

    }
}