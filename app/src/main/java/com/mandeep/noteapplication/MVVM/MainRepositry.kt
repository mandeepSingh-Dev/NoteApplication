package com.mandeep.noteapplication.MVVM

import androidx.lifecycle.LiveData
import com.mandeep.noteapplication.Room.DaoInterface
import com.mandeep.noteapplication.Room.Notes
import javax.inject.Inject

class MainRepositry @Inject constructor(private val daoInterface:DaoInterface)
{
   suspend fun insertNote(notes: Notes){
        daoInterface.insert(notes)
    }

    fun readNotes():LiveData<List<Notes>> = daoInterface.readNotes()

}