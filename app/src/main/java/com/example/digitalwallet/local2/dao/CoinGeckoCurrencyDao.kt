package com.example.digitalwallet.local2.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.digitalwallet.local.model.Currency

interface CoinGeckoCurrencyDao {
    @Query("SELECT * FROM Currency")
    fun getFavoriteListLive(): LiveData<List<Currency>>

    @Query("SELECT * FROM Currency")
    suspend fun getFavoriteList(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Currency)

    @Update
    suspend fun update(item: Currency)

    @Query("DELETE FROM Currency WHERE base = :base")
    suspend fun delete(base: String)

    @Query("DELETE FROM Currency")
    suspend fun delete()

}