package ru.vdh.foodrecipes.foodjoke.datasource

import android.content.Context
import ru.vdh.foodrecipes.foodjoke.data.datasource.NewFeatureDataSource


private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FIRST_NAME = "firstName"
private const val KEY_LAST_NAME = "lastName"
private const val DEFAULT_FIRST_NAME = "Default first name"
private const val DEFAULT_LAST_NAME = "Default last name"

class SharedPrefNewFeatureDataSource(context: Context) : NewFeatureDataSource {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

}