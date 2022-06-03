package com.mandeep.noteapplication.MVVM

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.noteapplication.R

class GridAdpaterr(val context: Context, val arrayList:ArrayList<Bitmap>):RecyclerView.Adapter<GridAdpaterr.MyViewHolder>(){



    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val constrintlayout = itemView.findViewById<ConstraintLayout>(R.id.constraintlayout)
        val imageview:ImageView = itemView.findViewById(R.id.imagevview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("883he",arrayList.size.toString())
      val view =LayoutInflater.from(context).inflate(R.layout.image_item,parent,false)
    return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imagestring  = arrayList.get(position)

        holder.imageview.layoutParams.width = imagestring.width
       // holder.imageview.layoutParams.height = imagestring.height
      //  holder.itemView.layoutParams.width = imagestring.width
        holder.constrintlayout.layoutParams.height = imagestring.height

        holder.imageview.setImageBitmap(imagestring)

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }
}