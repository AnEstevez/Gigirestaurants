package com.anestevez.gigirestaurants.core.local

import com.anestevez.gigirestaurants.core.toDomain
import com.anestevez.gigirestaurants.core.toEntity
import com.anestevez.gigirestaurants.data.datasources.LocalDataSource
import com.anestevez.gigirestaurants.data.models.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RoomDataSource @Inject constructor(private val dao: RestaurantDao) :
    LocalDataSource {

    override suspend fun getRestaurantById(id: Int): Restaurant =
        dao.getRestaurantById(id).toDomain()

    override fun getFavorites(): Flow<List<Restaurant>> =
        dao.getFavorites().map { it.map { restaurantEntity -> restaurantEntity.toDomain() } }
            .distinctUntilChanged()

    override suspend fun updateRestaurant(restaurant: Restaurant) =
        dao.updateRestaurant(restaurant.toEntity())

    override suspend fun insertRestaurantsList(restaurants: List<Restaurant>) =
        dao.insertRestaurantsList(restaurants.map { it.toEntity() })

}