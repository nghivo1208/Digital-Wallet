package com.example.digitalwallet.network2.service

import com.example.digitalwallet.network2.model.response.GetPriceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

        @GET("api/v3/coins/markets")
        suspend fun getListCoin(
            @Query("currency") currency: String,
            @Query("order") order: String,
            @Query("per_page") perPage: Int,
            @Query("page") page: Int
        ): List<GetPriceResponse>?

}