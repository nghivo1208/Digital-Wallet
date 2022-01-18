package com.example.digitalwallet.core.di.module

import com.example.digitalwallet.core.repository.CurrencyRepository
import com.example.digitalwallet.core.repository.CurrencyRepositoryImpl
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @get:[Binds Singleton]
    val CurrencyRepositoryImpl.homeRepository: CurrencyRepository

}
