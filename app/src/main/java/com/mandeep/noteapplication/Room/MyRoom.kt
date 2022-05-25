package com.mandeep.noteapplication.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database( entities = [Notes::class], version = 7, exportSchema = false)
abstract class MyRoom :RoomDatabase(){

    abstract fun dao():DaoInterface
}