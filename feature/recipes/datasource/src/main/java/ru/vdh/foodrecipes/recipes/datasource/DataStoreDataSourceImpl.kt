package ru.vdh.foodrecipes.recipes.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_DIET_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.PREFERENCES_BACK_ONLINE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.PREFERENCES_DIET_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import ru.vdh.foodrecipes.common.utils.Constants.Companion.PREFERENCES_MEAL_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import ru.vdh.foodrecipes.common.utils.Constants.Companion.PREFERENCES_NAME
import ru.vdh.foodrecipes.recipes.data.datasource.DataStoreDataSource
import ru.vdh.foodrecipes.recipes.data.model.MealAndDietTypeDataModel
import java.io.IOException

private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

class DataStoreDataSourceImpl (private val context: Context) :
    DataStoreDataSource {

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }

    private val dataStore: DataStore<Preferences> = context.dataStore

    override suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedMealTypeId] = mealTypeId
            preferences[PreferenceKeys.selectedDietType] = dietType
            preferences[PreferenceKeys.selectedDietTypeId] = dietTypeId
        }
    }

    override suspend fun saveBackOnline(backOnline: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    override val readMealAndDietType: Flow<MealAndDietTypeDataModel> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?: 0
            val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = preferences[PreferenceKeys.selectedDietTypeId] ?: 0
            MealAndDietTypeDataModel(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

    override val readBackOnline: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val backOnline = preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }

}
