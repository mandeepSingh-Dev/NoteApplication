package com.mandeep.noteapplication.MVVM

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandeep.noteapplication.Room.Images
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class MyViewModel2 @AssistedInject constructor( mainRepositry: MainRepositry, val context: Context, @Assisted val joinId:String):ViewModel() {

     var image: LiveData<Images> = MutableLiveData()

    init{
        image  = mainRepositry.getSingleImage(joinId)
    }


}