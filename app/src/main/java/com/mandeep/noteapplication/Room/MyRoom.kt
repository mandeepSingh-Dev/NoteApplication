package com.mandeep.noteapplication.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database( entities = [Notes::class,Images::class], version = 13, exportSchema = false)
@TypeConverters(TypeConvertrclass::class)
abstract class MyRoom :RoomDatabase(){

    abstract fun dao():DaoInterface
}