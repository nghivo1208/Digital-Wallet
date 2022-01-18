package com.example.digitalwallet.core.di.module

import android.app.Application
import android.content.Context
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.text.Typography.dagger

@InstallIn(SingletonComponent::class)
@dagger.Module
interface ContextModule {

    @get:dagger.Binds
    val Application.bindContext: Context

    //comment234 2423

    ///yutiyuiy
}
