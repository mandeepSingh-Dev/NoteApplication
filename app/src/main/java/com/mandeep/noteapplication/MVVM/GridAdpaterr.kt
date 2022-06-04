package com.mandeep.noteapplication.MVVM

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mandeep.noteapplication.R

class GridAdpaterr(val context: Context, val arrayList:ArrayList<Bitmap>):RecyclerView.Adapter<GridAdpaterr.MyViewHolder>(){



    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val constrintlayout = itemView.findViewById<ConstraintLayout>(R.id.constraintlayout)
        val imageview:ShapeableImageView = itemView.findViewById(R.id.imagevview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("883he",arrayList.size.toString())
      val view =LayoutInflater.from(context).inflate(R.layout.image_item,parent,false)
    return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageBitmap  = arrayList.get(position)

        holder.imageview.layoutParams.width = imageBitmap.width
       // holder.imageview.layoutParams.height = imagestring.height
      //  holder.itemView.layoutParams.width = imagestring.width
        holder.constrintlayout.layoutParams.height = imageBitmap.height

        //this is for set image in full width height when only singal image in list.
        if(arrayList.size == 1)
        {
            holder.itemView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            holder.itemView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }



        holder.imageview.setImageBitmap(imageBitmap)

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }
}