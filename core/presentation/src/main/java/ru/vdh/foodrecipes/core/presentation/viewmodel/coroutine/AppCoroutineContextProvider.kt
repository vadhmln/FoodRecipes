package ru.vdh.foodrecipes.core.presentation.viewmodel.coroutine

import kotlinx.coroutines.Dispatchers
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider

class AppCoroutineContextProvider : CoroutineContextProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
}
