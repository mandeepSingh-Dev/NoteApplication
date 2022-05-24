package com.mandeep.noteapplication.Room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database( entities = [Notes::class], version = 1, exportSchema = false)
abstract class MyRoom :RoomDatabase(){

    abstract fun dao():DaoInterface
}