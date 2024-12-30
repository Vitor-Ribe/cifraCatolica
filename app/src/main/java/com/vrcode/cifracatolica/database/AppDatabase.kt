package com.vrcode.cifracatolica.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vrcode.cifracatolica.dao.CifraDao
import com.vrcode.cifracatolica.model.Cifra

@Database(entities = [Cifra::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cifraDao(): CifraDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cifra_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
