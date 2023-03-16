package ru.vdh.foodrecipes.recipes.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.jsoup.Jsoup
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.ResultPresentationModel
import ru.vdh.foodrecipes.recipes.ui.R
import ru.vdh.foodrecipes.recipes.ui.databinding.RecipesRowLayoutBinding
import javax.inject.Inject

class RecipesAdapter @Inject constructor() : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private val delegateOnClickListener = DelegateOnClickListener()

    var onToDoItemClickListener: OnClickListener = delegateOnClickListener.onToDoItemClickListener

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
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData: RecipesPresentationModel) {
        Log.d("AAA", "RecipesAdapter setData")
        val recipesDiffUtil =
            RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    private fun applyVeganColor(view: View, vegan: Boolean) {
        if (vegan) {
            when (view) {
                is TextView -> {
                    view.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            ru.vdh.cleanarch.core.ui.R.color.green
                        )
                    )
                }

                is ImageView -> {
                    view.setColorFilter(
                        ContextCompat.getColor(
                            view.context,
                            ru.vdh.cleanarch.core.ui.R.color.green
                        )
                    )
                }
            }
        }
    }

    private fun parseHtml(textView: TextView, description: String?) {
        if (description != null) {
            val desc = Jsoup.parse(description).text()
            textView.text = desc
        }
    }

    interface OnClickListener {
        fun onToDoItemClick(toDoId: Int)

        object DoNothing : OnClickListener {
            override fun onToDoItemClick(toDoId: Int) = Unit
        }
    }
}