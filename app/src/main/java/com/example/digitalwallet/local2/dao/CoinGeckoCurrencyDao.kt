package com.example.digitalwallet.local2.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.digitalwallet.local2.model.CoinGeckoCurrency

interface CoinGeckoCurrencyDao {
    @Query("SELECT * FROM CoinGeckoCurrency")
    fun getFavoriteListLive(): LiveData<List<CoinGeckoCurrency>>

    @Query("SELECT * FROM CoinGeckoCurrency")
    suspend fun getFavoriteList(): List<CoinGeckoCurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CoinGeckoCurrency)

    @Update
    suspend fun update(item: CoinGeckoCurrency)

    @Query("DELETE FROM CoinGeckoCurrency WHERE base = :base")
    suspend fun delete(base: String)

    @Query("DELETE FROM CoinGeckoCurrency")
    suspend fun delete()

}