package ru.vdh.foodrecipes.recipes.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_DIET_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import ru.vdh.foodrecipes.recipes.presentation.viewmodel.RecipesFragmentViewModel
import ru.vdh.foodrecipes.recipes.ui.databinding.RecipesBottomSheetBinding
import java.lang.Exception
import java.util.Locale

@AndroidEntryPoint
class RecipesBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: RecipesFragmentViewModel by viewModels()

    private var _binding: RecipesBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = RecipesBottomSheetBinding.inflate(inflater, container, false)

        viewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
            Log.d("meal&dietTypeChipGroup", "$mealTypeChip, ${value.selectedMealTypeId}, $dietTypeChip, ${value.selectedDietTypeId}")
        }

        binding.mealTypeChipGroup.setOnCheckedStateChangeListener { group, _ ->
            val selectedChipId = group.checkedChipId
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChipId
            Log.d("meal&dietTypeChipGroup", "$mealTypeChip, $mealTypeChipId, $dietTypeChip, $dietTypeChipId")
        }

        binding.dietTypeChipGroup.setOnCheckedStateChangeListener { group, _ ->
            val selectedChipId = group.checkedChipId
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = selectedChipId
            Log.d("meal&dietTypeChipGroup", "$mealTypeChip, $mealTypeChipId, $dietTypeChip, $dietTypeChipId")
        }

        binding.applyBtn.setOnClickListener {
            viewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )
            Log.d("meal&dietTypeChipGroup", "$mealTypeChip, $mealTypeChipId, $dietTypeChip, $dietTypeChipId")
            val action =
                RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                val targetView = chipGroup.findViewById<Chip>(chipId)
                targetView.isChecked = true
                chipGroup.requestChildFocus(targetView, targetView)
            } catch (e: Exception) {
                Log.d("RecipesBottomSheet", e.message.toString())
            }
        }
    }

//    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
//        if (chipId != 0) {
//            try {
//                chipGroup.findViewById<Chip>(chipId).isChecked = true
//            } catch (e: Exception) {
//                Log.d("RecipesBottomSheet", e.message.toString())
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}