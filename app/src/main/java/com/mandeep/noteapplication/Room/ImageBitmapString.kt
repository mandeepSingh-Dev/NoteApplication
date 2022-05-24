package com.mandeep.noteapplication.Room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageBitmapString
{
    companion object{
        fun BitmapToString(bitmap: Bitmap){
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)

            val bytearray = byteArrayOutputStream.toByteArray()

            val temp = Base64.encodeToString(bytearray,Base64.DEFAULT) ?: return
        }

        fun StringtoBitmap(encodeString:String): Bitmap?
        {
            try{
               val encodedbyte = Base64.decode(encodeString,Base64.DEFAULT)
               val bitmap = BitmapFactory.decodeByteArray(encodedbyte,0,encodedbyte.size)

                if(bitmap == null)
                {
                    return null
                }else{
                    return bitmap
                }
            }catch (e:Exception){
                return null
            }
        }
    }
}
