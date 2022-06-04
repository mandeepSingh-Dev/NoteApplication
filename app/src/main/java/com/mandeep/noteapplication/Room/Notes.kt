package com.mandeep.noteapplication.Room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "Notes")
data class Notes(
    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "description")
    val description:String,

    @ColumnInfo(name = "joinId")
    val joinId:String,

    @ColumnInfo(name = "singleBitmap")
    val singleBitmap:Bitmap?
 )
{
    constructor(title:String,description: String,joinId: String):this(title,description,joinId,null)

    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
