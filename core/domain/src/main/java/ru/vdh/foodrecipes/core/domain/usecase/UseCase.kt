package ru.vdh.foodrecipes.core.domain.usecase

interface UseCase<REQUEST, RESULT> {
    suspend fun execute(input: REQUEST, onResult: (RESULT) -> Unit)
}
