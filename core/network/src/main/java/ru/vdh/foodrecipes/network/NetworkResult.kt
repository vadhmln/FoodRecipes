package ru.vdh.foodrecipes.network

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null): NetworkResult<T>(data, message)
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
    class Loading<T>: NetworkResult<T>()

}