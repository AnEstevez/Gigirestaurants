package com.anestevez.gigirestaurants.data.datasources

import com.anestevez.gigirestaurants.data.models.Restaurant

interface RemoteDataSource {
    suspend fun searchNearbyRestaurants(latLong: String): List<Restaurant>
    suspend fun searchRestaurantsByName(name: String): List<Restaurant>
    suspend fun getRestaurantById(id: Int): Restaurant
    suspend fun getRestaurantThumbById(id: Int): String

}
