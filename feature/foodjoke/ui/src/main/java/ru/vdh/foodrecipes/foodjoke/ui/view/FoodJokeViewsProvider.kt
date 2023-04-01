package ru.vdh.foodrecipes.foodjoke.ui.view

import android.widget.ProgressBar
import com.google.android.material.card.MaterialCardView
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider

interface FoodJokeViewsProvider : ViewsProvider {

    val progressBar: ProgressBar

    val materialCardView: MaterialCardView
}
