package com.example.digitalwallet.di

import com.example.digitalwallet.core.di.injectCoreComponent
import com.zanty.chresource.digitalcurrencieswallet.ui.main.MainActivity
import com.zanty.chresource.digitalcurrencieswallet.ui.search.SearchCurrencyFragment

internal fun MainActivity.injectComponent() = DaggerMainComponent
    .factory()
    .build(injectCoreComponent)
    .inject(this)

internal fun SearchCurrencyFragment.injectComponent() = DaggerMainComponent
    .factory()
    .build(injectCoreComponent)
    .inject(this)
