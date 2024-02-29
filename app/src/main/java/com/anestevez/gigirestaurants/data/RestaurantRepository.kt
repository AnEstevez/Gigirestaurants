package com.anestevez.gigirestaurants.data

import com.anestevez.gigirestaurants.core.location.LocationDataSource
import com.anestevez.gigirestaurants.data.datasources.RemoteDataSource
import com.anestevez.gigirestaurants.data.models.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val locationDataSource: LocationDataSource,
    ) {
    fun getRestaurantById(id: Int): Flow<Result<Restaurant>> = flow<Result<Restaurant>> {
        var restaurant = remoteDataSource.getRestaurantById(id)
        restaurant =
            restaurant.copy(thumbUrl = remoteDataSource.getRestaurantThumbById(restaurant.id!!))
        emit(Result.success(restaurant))
    }.catch {
        Timber.e(it)
        emit(Result.failure<Restaurant>(it))
    }

    suspend fun searchNearbyRestaurants(): Result<List<Restaurant>> {
        var restaurants = emptyList<Restaurant>()
        try {
            val latLong = locationDataSource.getLastLatLong()
            restaurants = remoteDataSource.searchNearbyRestaurants(latLong = latLong)

            if (restaurants.isNotEmpty()) {
                restaurants = restaurants.map {
                    it.copy(
                        thumbUrl = remoteDataSource.getRestaurantThumbById(it.id!!)
                    )
                }
            }

        } catch (it: Throwable) {
            Timber.e(it)
            Result.failure<List<Restaurant>>(it)
        }
        return Result.success(restaurants)
    }

    suspend fun searchRestaurantsByName(name: String): Result<List<Restaurant>> {
        var restaurants = emptyList<Restaurant>()
        try {
            restaurants = remoteDataSource.searchRestaurantsByName(name = name)

            if (restaurants.isNotEmpty()) {
                restaurants = restaurants.map {
                    it.copy(
                        thumbUrl = remoteDataSource.getRestaurantThumbById(it.id!!)
                    )
                }
            }

        } catch (it: Throwable) {
            Timber.e(it)
            Result.failure<List<Restaurant>>(it)
        }
        return Result.success(restaurants)
    }
}
