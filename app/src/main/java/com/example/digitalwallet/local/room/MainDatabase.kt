package com.example.digitalwallet.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digitalwallet.local.dao.CurrencyDao
import com.example.digitalwallet.local.model.Currency

@Database(
    entities = [Currency::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}
