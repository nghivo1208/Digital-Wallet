package com.example.digitalwallet.network2

import com.example.digitalwallet.network.service.CurrencyService
import com.example.digitalwallet.network2.service.CoinGeckoService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkFactory {

    private const val HTTP_TIME_OUT = 15L

    fun createDriverService(retrofit: Retrofit): CoinGeckoService =
        retrofit.create(CoinGeckoService::class.java)

    fun createRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        url: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun createOkHttpClient(isDebug: Boolean) = OkHttpClient.Builder()
        .addNetworkInterceptor(makeLoggingInterceptor(isDebug))
        .connectTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
        .build()

    private fun makeLoggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor()
        .apply {
            level = if (isDebug) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

}