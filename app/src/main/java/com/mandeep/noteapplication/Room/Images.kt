package com.mandeep.noteapplication.Room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Images")
data class Images(

    @ColumnInfo(name = "userId")
    var userId:String,

    @ColumnInfo(name = "bitmap")
    var image: Bitmap
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0;
}
