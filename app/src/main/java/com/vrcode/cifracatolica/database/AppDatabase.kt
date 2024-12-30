package com.vrcode.cifracatolica.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vrcode.cifracatolica.dao.CifraDao
import com.vrcode.cifracatolica.model.Cifra

@Database(entities = [Cifra::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cifraDao(): CifraDao
}
