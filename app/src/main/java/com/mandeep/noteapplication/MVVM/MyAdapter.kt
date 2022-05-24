package com.mandeep.noteapplication.MVVM

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.noteapplication.R
import com.mandeep.noteapplication.Room.Notes

class MyAdapter(val context: Context, val noteList:List<Notes>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = noteList.get(position)
        holder.titleTextView.text = note.title
        holder.descriptionTextView.text = note.description

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