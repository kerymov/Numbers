package com.example.numbers.data.network

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val data: T) : NetworkResult<T>()
    class Error<T : Any>(val e: Throwable) : NetworkResult<T>()
}

suspend fun <T:Any> handleApi(
    execute: suspend() -> T
): NetworkResult<T> = try {
    val response = execute()
    NetworkResult.Success(response)
} catch (e: Throwable) {
    NetworkResult.Error(e)
}