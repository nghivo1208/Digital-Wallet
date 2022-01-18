package com.example.digitalwallet.core.di.module

import com.example.digitalwallet.core.executor.PostExecutionThread
import com.example.digitalwallet.core.executor.PostExecutionThreadImpl
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}
