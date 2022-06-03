package com.mandeep.noteapplication.MVVM

import dagger.assisted.AssistedFactory

@AssistedFactory
interface MyViewModel2AssistedFactory {

    fun create(joinId:String):MyViewModel2
}