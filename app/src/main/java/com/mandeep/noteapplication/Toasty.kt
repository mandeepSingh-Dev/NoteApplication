package com.mandeep.noteapplication

import android.content.Context
import android.widget.Toast

object Toasty {
    fun message(context: Context, msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    fun pp2(name:String):Boolean{

return name.equals("mandeep")
    }}