package com.vrcode.cifracatolica.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cifra_database"
                ).build()
            }
        }
        return INSTANCE!!
    }
}
