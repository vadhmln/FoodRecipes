package ru.vdh.foodrecipes.recipes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.recipes.data.datasource.DataStoreDataSource
import ru.vdh.foodrecipes.recipes.data.mapper.DataStoreDataToDomainMapper
import ru.vdh.foodrecipes.recipes.domain.model.MealAndDietTypeDomainModel
import ru.vdh.foodrecipes.recipes.domain.repository.DataStoreRepository

class DataStoreRepositoryImpl(
    private val dataStoreDataSource: DataStoreDataSource,
    private val dataStoreDataToDomainMapper: DataStoreDataToDomainMapper
): DataStoreRepository {

    override suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) =
        dataStoreDataSource.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)

    override suspend fun saveBackOnline(backOnline: Boolean) =
        dataStoreDataSource.saveBackOnline(backOnline)

    override val readMealAndDietType: Flow<MealAndDietTypeDomainModel> =
        dataStoreDataSource.readMealAndDietType.map(dataStoreDataToDomainMapper::toDomain)

    override val readBackOnline: Flow<Boolean> =
        dataStoreDataSource.readBackOnline
}