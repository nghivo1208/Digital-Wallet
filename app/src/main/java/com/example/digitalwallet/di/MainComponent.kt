package com.example.digitalwallet.di

import com.example.digitalwallet.core.di.CoreComponent
import com.zanty.chresource.digitalcurrencieswallet.ui.main.MainActivity
import com.zanty.chresource.digitalcurrencieswallet.ui.search.SearchCurrencyFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(searchCurrencyFragment: SearchCurrencyFragment)

    @Component.Factory
    interface Factory {
        fun build(coreComponent: CoreComponent): MainComponent
    }
}
