package ru.vdh.foodrecipes.recipes.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.vdh.foodrecipes.common.utils.applyVeganColor
import ru.vdh.foodrecipes.common.utils.parseHtml
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.ResultPresentationModel
import ru.vdh.foodrecipes.recipes.ui.R
import javax.inject.Inject

class RecipesAdapter @Inject constructor() : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private val delegateOnClickListener = DelegateOnClickListener()
    var onRecipeItemClickListener: OnClickListener = delegateOnClickListener.onToDoItemClickListener

    private var recipes = emptyList<ResultPresentationModel>()

    class MyViewHolder @Inject constructor(
        private val onClickListener: OnClickListener,
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        val recipeImage: ImageView by lazy { view.findViewById(R.id.recipe_imageView) }
        val title: TextView by lazy { view.findViewById(R.id.title_textView) }
        val description: TextView by lazy { view.findViewById(R.id.description_textView) }
        val heartImage: ImageView by lazy { view.findViewById(R.id.heart_imageView) }
        val heartTextView: TextView by lazy { view.findViewById(R.id.heart_textView) }
        val clockImage: ImageView by lazy { view.findViewById(R.id.clock_imageView) }
        val clockTextView: TextView by lazy { view.findViewById(R.id.clock_textView) }
        val leafImage: ImageView by lazy { view.findViewById(R.id.leaf_imageView) }
        val leafTextView: TextView by lazy { view.findViewById(R.id.leaf_textView) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recipes_row_layout, parent, false)
            .let { view -> MyViewHolder(delegateOnClickListener, view) }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.recipeImage.load(currentRecipe.image) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
        holder.title.text = currentRecipe.title
        parseHtml(holder.description, currentRecipe.summary)
        holder.heartTextView.text = currentRecipe.aggregateLikes.toString()
        holder.clockTextView.text = currentRecipe.readyInMinutes.toString()
        applyVeganColor(holder.leafImage, currentRecipe.vegan)
        applyVeganColor(holder.leafTextView, currentRecipe.vegan)
        holder.itemView.setOnClickListener {
            Log.d("onRecipeItemClick", "Clicked!!!")

            onRecipeItemClickListener.onRecipeItemClick(currentRecipe.recipeId)
            Log.d("onRecipeItemClick", currentRecipe.recipeId.toString())
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData: RecipesPresentationModel) {
        Log.d("AAA", "RecipesAdapter setData")
        val recipesDiffUtil =
            ru.vdh.foodrecipes.common.utils.RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = androidx.recyclerview.widget.DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

    interface OnClickListener {
        fun onRecipeItemClick(recipeId: Int)

        object DoNothing : OnClickListener {
            override fun onRecipeItemClick(recipeId: Int) = Unit
        }
    }
}