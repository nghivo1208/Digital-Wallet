package com.example.digitalwallet.core.di

import com.squareup.moshi.Moshi
import com.example.digitalwallet.core.executor.PostExecutionThread
import com.example.digitalwallet.core.repository.CurrencyRepository
import com.example.digitalwallet.network.service.CurrencyService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CoreComponent {
    val postExecutionThread: PostExecutionThread
    val moshi: Moshi
    val currencyService: CurrencyService
    val currencyRepository: CurrencyRepository
}
