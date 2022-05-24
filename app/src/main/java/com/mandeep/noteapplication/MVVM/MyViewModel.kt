package com.mandeep.noteapplication.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandeep.noteapplication.Room.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val mainRepositry: MainRepositry): ViewModel() {

    var listNotes:LiveData<List<Notes>>?=null

    init {
        viewModelScope.launch {
            listNotes = MutableLiveData()
            val list = mainRepositry.readNotes()
           listNotes = list
        }
    }

    fun insertNote(notes: Notes){
       viewModelScope.launch {
           mainRepositry.insertNote(notes)
       }
    }

}