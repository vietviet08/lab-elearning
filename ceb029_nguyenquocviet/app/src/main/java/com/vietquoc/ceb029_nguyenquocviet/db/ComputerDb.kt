package com.vietquoc.ceb029_nguyenquocviet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vietquoc.ceb029_nguyenquocviet.model.Computer

@Database(entities = [Computer::class], version = 1)
abstract class ComputerDb : RoomDatabase() {
    abstract fun getNoteDao(): ComputerDao
}
