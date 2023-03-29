package ru.vdh.foodrecipes.favoriterecipes.ui.adapter

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import ru.vdh.foodrecipes.common.utils.RecipesDiffUtil
import ru.vdh.foodrecipes.common.utils.applyVeganColor
import ru.vdh.foodrecipes.common.utils.parseHtml
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoritesPresentationModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.viewmodel.FavoriteRecipesFragmentViewModel
import ru.vdh.foodrecipes.favoriterecipes.ui.R
import ru.vdh.foodrecipes.favoriterecipes.ui.databinding.FavoriteRecipesRowLayoutBinding
import javax.inject.Inject

class FavoriteRecipesAdapter @Inject constructor(
    private val requireActivity: FragmentActivity,
    private val viewModel: FavoriteRecipesFragmentViewModel,
) : RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder>(), ActionMode.Callback {

    private val delegateOnClickListener = DelegateOnClickListener()
    private var onFavoriteRecipeClickListener: OnClickListener = delegateOnClickListener.onFavoriteRecipeClickListener

    private var multiSelection = false

    private lateinit var mActionMode: ActionMode
    private lateinit var rootView: View

    private var selectedRecipes = arrayListOf<FavoritesPresentationModel>()
    private var myViewHolders = arrayListOf<MyViewHolder>()
    private var favoriteRecipes = emptyList<FavoritesPresentationModel>()

    private lateinit var binding: FavoriteRecipesRowLayoutBinding

    class MyViewHolder(
        private val binding: FavoriteRecipesRowLayoutBinding,
        private val onClickListener: OnClickListener,
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView by lazy { view.findViewById(R.id.favorite_recipe_imageView) }
        val title: TextView by lazy { view.findViewById(R.id.favorite_title_textView) }
        val description: TextView by lazy { view.findViewById(R.id.favorite_description_textView) }
        val heartImage: ImageView by lazy { view.findViewById(R.id.favorite_heart_imageView) }
        val heartTextView: TextView by lazy { view.findViewById(R.id.favorite_heart_textView) }
        val clockImage: ImageView by lazy { view.findViewById(R.id.favorite_clock_imageView) }
        val clockTextView: TextView by lazy { view.findViewById(R.id.favorite_clock_textView) }
        val leafImage: ImageView by lazy { view.findViewById(R.id.favorite_leaf_imageView) }
        val leafTextView: TextView by lazy { view.findViewById(R.id.favorite_leaf_textView) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_recipes_row_layout, parent, false)
//            .let { view -> MyViewHolder(binding, delegateOnClickListener, view) }
        return MyViewHolder(binding, delegateOnClickListener, view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        myViewHolders.add(holder)
        rootView = holder.itemView.rootView

        val currentRecipe = favoriteRecipes[position]
        holder.recipeImage.load(currentRecipe.result.image) {
            crossfade(600)
            error(ru.vdh.cleanarch.core.ui.R.drawable.ic_error_placeholder)
        }
        holder.title.text = currentRecipe.result.title
        parseHtml(holder.description, currentRecipe.result.summary)
        holder.heartTextView.text = currentRecipe.result.aggregateLikes.toString()
        holder.clockTextView.text = currentRecipe.result.readyInMinutes.toString()
        applyVeganColor(holder.leafImage, currentRecipe.result.vegan)
        applyVeganColor(holder.leafTextView, currentRecipe.result.vegan)

        saveItemStateOnScroll(currentRecipe, holder)

        /**
         * Single Click Listener
         * */
        holder.itemView.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, currentRecipe)
            } else {
                onFavoriteRecipeClickListener.onFavoriteRecipeClick(currentRecipe.id)
            }
        }

        /**
         * Long Click Listener
         * */
        holder.itemView.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentRecipe)
                true
            } else {
                applySelection(holder, currentRecipe)
                true
            }
        }
    }

    private fun saveItemStateOnScroll(
        currentRecipe: FavoritesPresentationModel,
        holder: MyViewHolder
    ) {
        if (selectedRecipes.contains(currentRecipe)) {
            changeRecipeStyle(
                holder,
                ru.vdh.cleanarch.core.ui.R.color.cardBackgroundColor,
                ru.vdh.cleanarch.core.ui.R.color.colorPrimary
            )
        } else {
            changeRecipeStyle(
                holder,
                ru.vdh.cleanarch.core.ui.R.color.cardBackgroundColor,
                ru.vdh.cleanarch.core.ui.R.color.strokeColor
            )
        }
    }

    private fun applySelection(holder: MyViewHolder, currentRecipe: FavoritesPresentationModel) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
            changeRecipeStyle(
                holder,
                ru.vdh.cleanarch.core.ui.R.color.cardBackgroundColor,
                ru.vdh.cleanarch.core.ui.R.color.strokeColor
            )
            applyActionModeTitle()
        } else {
            selectedRecipes.add(currentRecipe)
            changeRecipeStyle(
                holder,
                ru.vdh.cleanarch.core.ui.R.color.cardBackgroundColor,
                ru.vdh.cleanarch.core.ui.R.color.colorPrimary
            )
            applyActionModeTitle()
        }
    }

    private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.itemView.setBackgroundColor(
            ContextCompat.getColor(requireActivity, backgroundColor)
        )
        binding.favoriteRowCardView.strokeColor =
            ContextCompat.getColor(requireActivity, strokeColor)
    }

    private fun applyActionModeTitle() {
        when (selectedRecipes.size) {
            0 -> {
                mActionMode.finish()
                multiSelection = false
            }

            1 -> {
                mActionMode.title = "${selectedRecipes.size} item selected"
            }

            else -> {
                mActionMode.title = "${selectedRecipes.size} items selected"
            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        mActionMode = actionMode!!
        applyStatusBarColor(ru.vdh.cleanarch.core.ui.R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {
        if (menu?.itemId == R.id.delete_favorite_recipe_menu) {
            selectedRecipes.forEach {
//                mainViewModel.deleteFavoriteRecipe(it)
            }
            showSnackBar("${selectedRecipes.size} Recipe/s removed.")

            multiSelection = false
            selectedRecipes.clear()
            actionMode?.finish()
        }
        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {
        myViewHolders.forEach { holder ->
            changeRecipeStyle(
                holder,
                ru.vdh.cleanarch.core.ui.R.color.cardBackgroundColor,
                ru.vdh.cleanarch.core.ui.R.color.strokeColor
            )
        }
        multiSelection = false
        selectedRecipes.clear()
        applyStatusBarColor(ru.vdh.cleanarch.core.ui.R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor =
            ContextCompat.getColor(requireActivity, color)
    }

    fun setData(newFavoriteRecipes: List<FavoritesPresentationModel>) {
        val favoriteRecipesDiffUtil =
            RecipesDiffUtil(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            rootView,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}
            .show()
    }

    fun clearContextualActionMode() {
        if (this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
    }

    interface OnClickListener {
        fun onFavoriteRecipeClick(recipeId: Int)

        object DoNothing : OnClickListener {
            override fun onFavoriteRecipeClick(recipeId: Int) = Unit
        }
    }

}