package com.example.digitalwallet.local2.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digitalwallet.local.dao.CurrencyDao
import com.example.digitalwallet.local.model.Currency
import com.example.digitalwallet.local2.dao.CoinGeckoCurrencyDao
import com.example.digitalwallet.local2.model.CoingeckoCurrency

@Database(
    entities = [Currency::class, CoingeckoCurrency::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun coinGeckoCurrencyDao(): CoinGeckoCurrencyDao
}