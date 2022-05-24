package com.mandeep.noteapplication.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.mandeep.noteapplication.Room.DaoInterface
import com.mandeep.noteapplication.Room.Notes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainRepositry @Inject constructor(private val daoInterface:DaoInterface)
{
   suspend fun insertNote(notes: Notes){
        daoInterface.insert(notes)
    }
    fun readNotes():LiveData<List<Notes>> = daoInterface.readNotes()

    fun readNote(id:Int):Notes = daoInterface.readNote(id)
}