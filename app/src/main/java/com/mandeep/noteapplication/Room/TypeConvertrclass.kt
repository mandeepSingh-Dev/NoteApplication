package com.mandeep.noteapplication.Room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

object TypeConvertrclass
{
    @TypeConverter
    fun BitMapToString(bitmap: Bitmap?):String?{
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

            val bytearray = byteArrayOutputStream.toByteArray()

            val tempStr = Base64.encodeToString(bytearray, Base64.DEFAULT)

            if (tempStr == null) {
                return null
            } else {
                return tempStr
            }
        }catch (e:Exception)
        {
            return null
        }
    }


    @TypeConverter
    fun StringToBitMap(string:String):Bitmap?
    {
        try {
            val bytearray = Base64.decode(string, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
            if (bitmap == null) {
                return null
            } else {
               return bitmap
            }
        }catch (e:Exception){
            return null
        }

    }



}