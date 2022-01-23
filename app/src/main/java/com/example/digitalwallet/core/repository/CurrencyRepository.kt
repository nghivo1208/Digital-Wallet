package com.example.digitalwallet.core.repository

import androidx.lifecycle.LiveData
import com.example.digitalwallet.core.network.BaseResult
import com.example.digitalwallet.local.model.Currency
import com.example.digitalwallet.local2.model.CoinGeckoCurrency
import com.example.digitalwallet.network2.model.response.CoinGeckoCurrencyResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    // Lấy từ api
    fun getList(): Flow<BaseResult<List<CoinGeckoCurrencyResponse?>>>

    // Lấy từ room
    val coinGeckoList: LiveData<List<CoinGeckoCurrency>> // Entity

    // Lưu vào room
    suspend fun insertCurrency(item: CoinGeckoCurrency)

//    val favoriteList: LiveData<List<Currency>>
//
//    suspend fun insertOrDeleteCurrencyToFavorite(item: Currency)
//
//    suspend fun updateFavorite(currencies: List<Currency>)

}
