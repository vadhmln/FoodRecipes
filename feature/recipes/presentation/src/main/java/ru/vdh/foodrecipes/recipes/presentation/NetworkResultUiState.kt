package ru.vdh.foodrecipes.recipes.presentation

sealed class NetworkResultUiState<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T): NetworkResultUiState<T>(data)
    class Error<T>(message: String?, data: T? = null): NetworkResultUiState<T>(data, message)
    class Loading<T>: NetworkResultUiState<T>()

}