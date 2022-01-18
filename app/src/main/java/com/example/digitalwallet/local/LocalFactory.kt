package com.example.digitalwallet.local

import android.content.Context
import androidx.room.Room
import com.example.digitalwallet.local.room.MainDatabase

object LocalFactory {
    private const val DATABASE_NAME = "ch_resource_local_db"

    fun makeRoomDatabase(context: Context): MainDatabase = Room
        .databaseBuilder(
            context,
            MainDatabase::class.java,
            DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
}
