package ru.vdh.foodrecipes.app

import android.app.Application
import androidx.activity.viewModels
import dagger.hilt.android.HiltAndroidApp
import ru.vdh.foodrecipes.recipedetails.presentation.viewmodel.RecipeDetailsViewModel

@HiltAndroidApp
internal class App : Application()