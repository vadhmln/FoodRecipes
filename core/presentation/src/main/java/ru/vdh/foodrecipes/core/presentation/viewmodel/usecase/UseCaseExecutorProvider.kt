package ru.vdh.foodrecipes.core.presentation.viewmodel.usecase

import kotlinx.coroutines.CoroutineScope
import ru.vdh.foodrecipes.core.domain.usecase.UseCaseExecutor

typealias UseCaseExecutorProvider =
    @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor
