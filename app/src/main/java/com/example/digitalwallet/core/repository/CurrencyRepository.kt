package com.example.digitalwallet.core.repository

import androidx.lifecycle.LiveData
import com.example.digitalwallet.core.network.BaseResult
import com.example.digitalwallet.local.model.Currency
import com.example.digitalwallet.network.model.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getList(): Flow<BaseResult<List<CurrencyResponse?>>>

    val favoriteList: LiveData<List<Currency>>

    suspend fun insertOrDeleteCurrencyToFavorite(item: Currency)

    suspend fun updateFavorite(currencies: List<Currency>)

}
