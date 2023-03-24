package ru.vdh.foodrecipes.recipedetails.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.vdh.foodrecipes.recipedetails.ui.view.RecipeDetailsFragment

class PagerAdapter(
    private val resultBundle: Bundle,
    private val fragments: ArrayList<Fragment>,
    recipeDetailsFragment: RecipeDetailsFragment
) : FragmentStateAdapter(recipeDetailsFragment) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        fragments[position].arguments = resultBundle
        return fragments[position]
    }
}