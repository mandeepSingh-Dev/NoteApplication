package com.mandeep.noteapplication.di

import android.content.Context
import androidx.room.Room
import com.mandeep.noteapplication.Room.MyRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    var databaseName = "Notes_Database"

    lateinit var context: Context

    @Provides
    fun provideContext(@ApplicationContext context:Context):Context{
        this.context = context
        return context
    }


    @Singleton
    @Provides
    fun provideDao(@ApplicationContext context: Context) = Room.databaseBuilder(context, MyRoom::class.java, databaseName).fallbackToDestructiveMigration().build().dao()
}