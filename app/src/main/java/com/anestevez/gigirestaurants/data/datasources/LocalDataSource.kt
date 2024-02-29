package com.anestevez.gigirestaurants.data.datasources

import com.anestevez.gigirestaurants.data.models.Restaurant
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getRestaurantById(id: Int): Restaurant

    fun getFavorites(): Flow<List<Restaurant>>

    suspend fun updateRestaurant(restaurant: Restaurant)

    suspend fun insertRestaurantsList(restaurants: List<Restaurant>)
}