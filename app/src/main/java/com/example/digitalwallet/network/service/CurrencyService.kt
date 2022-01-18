package com.example.digitalwallet.network.service

import com.example.digitalwallet.network.model.response.GetPricesResponse
import com.example.digitalwallet.network.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("price/all_prices_for_mobile")
    suspend fun getPrices(
        @Query("counter_currency") counterCurrency: String = Constants.CURRENCY
    ): GetPricesResponse?

}
