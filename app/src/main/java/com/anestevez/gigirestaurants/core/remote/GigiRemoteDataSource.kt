package com.anestevez.gigirestaurants.core.remote

import com.anestevez.gigirestaurants.core.remote.models.search.Data
import com.anestevez.gigirestaurants.core.toDomain
import com.anestevez.gigirestaurants.data.datasources.RemoteDataSource
import com.anestevez.gigirestaurants.data.models.Restaurant
import javax.inject.Inject
import javax.inject.Named

class GigiRemoteDataSource @Inject constructor(
    private val remoteService: RemoteService,
    @Named("apiKey") private val apiKey: String,
) : RemoteDataSource {

    override suspend fun searchNearbyRestaurants(latLong: String): List<Restaurant> =
        remoteService.searchNearbyRestaurants(latLong = latLong, apiKey = apiKey).data.map {
            it.toDomain()
        }

    override suspend fun searchRestaurantsByName(name: String): List<Restaurant> =
        remoteService.searchRestaurantsByName(searchQuery = name, apiKey = apiKey).data.map {
            it.toDomain()
        }

    override suspend fun getRestaurantById(id: Int): Restaurant =
        remoteService.getRestaurantById(
            id = id,
            apiKey = apiKey
        ).toDomain()

    override suspend fun getRestaurantThumbById(id: Int): String {
        var url = ""
        val data = remoteService.getRestaurantThumbById(
            id = id,
            apiKey = apiKey
        ).data

        if (data.isNotEmpty()){
           url =  data[0].images.large.url
        }
        return url
    }



}





