package ru.vdh.foodrecipes.favoriterecipes.datasource

import android.content.Context
import ru.vdh.foodrecipes.favoriterecipes.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.favoriterecipes.data.model.NewFeatureDataModel

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FIRST_NAME = "firstName"
private const val KEY_LAST_NAME = "lastName"
private const val DEFAULT_FIRST_NAME = "Default first name"
private const val DEFAULT_LAST_NAME = "Default last name"

class SharedPrefNewFeatureDataSource(context: Context) : NewFeatureDataSource {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(newFeatureDataModel: NewFeatureDataModel): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, newFeatureDataModel.firstName).apply()
        sharedPreferences.edit().putString(KEY_LAST_NAME, newFeatureDataModel.lastName).apply()
        return true
    }

    override fun get(): NewFeatureDataModel {
        val firstName =
            sharedPreferences.getString(KEY_FIRST_NAME, DEFAULT_FIRST_NAME) ?: DEFAULT_FIRST_NAME
        val lastName =
            sharedPreferences.getString(KEY_LAST_NAME, DEFAULT_LAST_NAME) ?: DEFAULT_LAST_NAME
        return NewFeatureDataModel(firstName = firstName, lastName = lastName)
    }
}