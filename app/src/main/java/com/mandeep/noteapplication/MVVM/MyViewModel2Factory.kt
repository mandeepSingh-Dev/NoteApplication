package com.mandeep.noteapplication.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MyViewModel2Factory @Inject constructor( var assistedFactory: MyViewModel2AssistedFactory,  var joinId:String):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(joinId) as T
    }

}