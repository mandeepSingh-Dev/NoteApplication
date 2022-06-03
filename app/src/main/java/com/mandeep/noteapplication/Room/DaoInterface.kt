package com.mandeep.noteapplication.Room

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoInterface{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes:Notes)

    @Query("SELECT * FROM Notes ORDER BY id DESC")
    fun readNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE id Like :idd")
    fun readNote(idd:Int):Notes

    @Insert
    suspend fun insertImage(images:Images)

    @Query("SELECT * FROM IMAGES")
    fun getImages():LiveData<List<Images>>

    @Query(" SELECT * FROM IMAGES WHERE userId Like :id")
    fun getSingleImage(id:String):LiveData<Images>


}