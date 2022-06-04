package com.mandeep.noteapplication.MVVM

import androidx.lifecycle.LiveData
import com.mandeep.noteapplication.Room.DaoInterface
import com.mandeep.noteapplication.Room.Images
import com.mandeep.noteapplication.Room.Notes
import javax.inject.Inject

class MainRepositry @Inject constructor(private val daoInterface:DaoInterface)
{
   suspend fun insertNote(notes: Notes){
        daoInterface.insert(notes)
    }

    fun readNotes():LiveData<List<Notes>> = daoInterface.readNotes()

    suspend fun insertImage(images: Images)
    {
        daoInterface.insertImage(images)
    }

    fun getImages():LiveData<List<Images>> = daoInterface.getImages()

    fun getSingleImage(id:String): LiveData<List<Images>> = daoInterface.getSingleImage(id)


}