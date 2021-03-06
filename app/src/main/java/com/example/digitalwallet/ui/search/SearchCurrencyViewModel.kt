package com.zanty.chresource.digitalcurrencieswallet.ui.search

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.*
import com.example.digitalwallet.core.executor.PostExecutionThread
import com.example.digitalwallet.core.repository.CurrencyRepository
import com.example.digitalwallet.local.model.Currency
import com.zanty.chresource.digitalcurrencieswallet.R
import com.zanty.chresource.digitalcurrencieswallet.ui.search.usecase.GetPricesUseCase
import com.zanty.chresource.digitalcurrencieswallet.ui.search.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCurrencyViewModel @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val getPricesUseCase: GetPricesUseCase,
    private val searchUseCase: SearchUseCase,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    val showLoading get() = getPricesUseCase.loadingLive
    val showReloadButton get() = getPricesUseCase.errorLive.map { !it.isNullOrBlank() }

    init {
        getPricesUseCase.fetchPricesFlow.launchIn(viewModelScope)
    }

    // Handle on click
    fun onClickReload() {
        getPricesUseCase.fetchPricesFlow.launchIn(viewModelScope)
    }

    val switchModeTransitionListener = object : MotionLayout.TransitionListener {
        override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        }

        override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        }

        override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            when (currentId) {
                R.id.left -> mSearchViewModeFlow.run {
                    if (value != SearchViewMode.FAVORITE) viewModelScope.launch {
                        delay(100)
                        value = SearchViewMode.FAVORITE
                    }
                }

                R.id.right -> mSearchViewModeFlow.run {
                    if (value != SearchViewMode.SEARCH) viewModelScope.launch {
                        delay(100)
                        value = SearchViewMode.SEARCH
                    }
                }
            }
        }

        override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
        }

    }

    // Mode
    private val mSearchViewModeFlow = MutableStateFlow(SearchViewMode.SEARCH)
    val viewMode: SearchViewMode get() = mSearchViewModeFlow.value
    private val mFavoriteListFlow get() = currencyRepository.favoriteList.asFlow()

    // Search
    private val mQueryStringFlow = MutableStateFlow("")
    val hasQueryString
        get() = mQueryStringFlow.map { it.isNotBlank() }.asLiveData(viewModelScope.coroutineContext)
    var queryString: String
        get() = mQueryStringFlow.value
        set(value) {
            mQueryStringFlow.value = value
        }

    private val mSearchListFlow = mQueryStringFlow
        .combine(getPricesUseCase.resultListLive.asFlow()) { query, list ->
            if (query.isBlank()) list
            else searchUseCase.searchResultList(list)(query)
        }
        .flowOn(postExecutionThread.io)

    val currencyListLive = mSearchViewModeFlow
        .flatMapLatest {
            when (it) {
                SearchViewMode.SEARCH -> mSearchListFlow
                SearchViewMode.FAVORITE -> mFavoriteListFlow
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    val emptyList get() = currencyListLive.map { it.isEmpty() }

    fun addCurrencyToFavoriteList(item: Currency) {
        viewModelScope.launch { currencyRepository.insertOrDeleteCurrencyToFavorite(item) }
    }

}
