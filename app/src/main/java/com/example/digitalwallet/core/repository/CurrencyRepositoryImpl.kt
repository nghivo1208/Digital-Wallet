package com.example.digitalwallet.core.repository

import androidx.lifecycle.LiveData
import com.example.digitalwallet.core.executor.PostExecutionThread
import com.example.digitalwallet.core.network.flowOfService
import com.example.digitalwallet.local.dao.CurrencyDao
import com.example.digitalwallet.local.model.Currency
import com.example.digitalwallet.network.service.CurrencyService
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val postExecutionThread: PostExecutionThread,
    private val currencyDao: CurrencyDao,
    private val currencyService: CurrencyService
) : CurrencyRepository {

    override fun getList() = flowOfService(postExecutionThread) {
        currencyService.getPrices()?.data ?: emptyList()
    }

    override val favoriteList: LiveData<List<Currency>>
        get() = currencyDao.getFavoriteListLive()

    override suspend fun insertOrDeleteCurrencyToFavorite(item: Currency) {
        if (item.isFavorite) currencyDao.insert(item)
        else currencyDao.delete(item.base)
    }

    override suspend fun updateFavorite(currencies: List<Currency>) {
        val favoriteList = currencyDao.getFavoriteList()
        favoriteList.forEach { oldItem ->
            currencies.firstOrNull { it.base == oldItem.base }
                ?.let { newItem ->
                    newItem.isFavorite = true
                    currencyDao.update(newItem)
                }
                ?: currencyDao.delete(oldItem.base)
        }
    }

}
