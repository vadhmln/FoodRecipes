package ru.vdh.foodrecipes.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination
import ru.vdh.foodrecipes.core.presentation.viewmodel.livedata.SingleLiveEvent
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.core.domain.exception.DomainException
import ru.vdh.foodrecipes.core.domain.usecase.UseCase

abstract class BaseViewModel<VIEW_STATE : Any, NOTIFICATION : Any>(
    useCaseExecutorProvider: UseCaseExecutorProvider
) : ViewModel() {
    private val _viewState = MutableLiveData<VIEW_STATE>()
        .apply { value = initialState() }
    val viewState = _viewState.asLiveData()
    private val _notification = SingleLiveEvent<NOTIFICATION>()
    val notification = _notification.asLiveData()
    private val _destination = SingleLiveEvent<PresentationDestination>()
    val destination = _destination.asLiveData()

    protected abstract fun initialState(): VIEW_STATE

    private val currentViewState: VIEW_STATE
        get() = viewState.value ?: initialState()

    private val useCaseExecutor by lazy {
        useCaseExecutorProvider(viewModelScope)
    }

    protected fun <OUTPUT> execute(
        useCase: UseCase<Unit, OUTPUT>,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        execute(useCase, Unit, onSuccess, onException)
    }

    protected fun <INPUT, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        useCaseExecutor.execute(useCase, value, onSuccess, onException)
    }

    protected fun updateViewState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
    }

    protected fun updateViewState(
        updatedState: VIEW_STATE.() -> VIEW_STATE
    ) = updateViewState(currentViewState.updatedState())

    protected fun notify(notification: NOTIFICATION) {
        _notification.value = notification
    }

    protected fun navigateTo(destination: PresentationDestination) {
        _destination.value = destination
    }

    protected fun navigateBack() {
        _destination.value = PresentationDestination.Back
    }

    private fun <T> LiveData<T>.asLiveData() = this
}
