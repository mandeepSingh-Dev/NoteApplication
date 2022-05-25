package com.mandeep.noteapplication.Room

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
 )
{
   // constructor(title:String,description: String):this(title,description,null)

    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
