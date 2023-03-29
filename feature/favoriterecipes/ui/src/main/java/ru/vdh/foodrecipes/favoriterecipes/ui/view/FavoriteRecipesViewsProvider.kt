package ru.vdh.foodrecipes.favoriterecipes.ui.view

import android.widget.ImageView
import android.widget.TextView
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider

interface FavoriteRecipesViewsProvider : ViewsProvider {

    val noDataImageView: ImageView

    val noDataTextView: TextView
}
