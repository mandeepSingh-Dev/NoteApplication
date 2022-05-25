package com.mandeep.noteapplication.Room

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


}