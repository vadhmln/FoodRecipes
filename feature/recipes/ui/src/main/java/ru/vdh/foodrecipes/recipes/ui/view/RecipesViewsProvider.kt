package ru.vdh.foodrecipes.recipes.ui.view

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider

interface RecipesViewsProvider : ViewsProvider {

    val recyclerView: RecyclerView

    val shimmerFrameLayout: ShimmerFrameLayout

    val errorImageView: ImageView

    val errorTextView: TextView

}
