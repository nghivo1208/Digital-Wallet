package com.example.digitalwallet.core.di.module

import android.content.Context
import com.example.digitalwallet.local.LocalFactory
import com.example.digitalwallet.local.dao.CurrencyDao
import com.example.digitalwallet.local.room.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(context: Context) = LocalFactory.makeRoomDatabase(context)

    @[Provides Singleton]
    fun provideCurrentDao(database: MainDatabase): CurrencyDao = database.currencyDao()

}
