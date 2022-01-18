package com.example.digitalwallet.core.network

import com.example.digitalwallet.core.executor.PostExecutionThread
import kotlinx.coroutines.flow.*
import retrofit2.HttpException

@Suppress("USELESS_CAST")
fun <T> flowOfService(
    postExecutionThread: PostExecutionThread,
    block: suspend () -> T
) = flow { emit(block.invoke()) }
    .map { response -> BaseResult.Success(data = response) as BaseResult<T> }
    .catch { cause ->
        val result = if (cause is HttpException) {
            BaseResult.Failed(cause.code(), cause.message())
        } else {
            BaseResult.Error(cause)
        }
        emit(result)
    }
    .onStart { emit(BaseResult.Loading) }
    .flowOn(postExecutionThread.io)
