package com.anestevez.gigirestaurants.domain

import com.anestevez.gigirestaurants.data.RestaurantRepository
import com.anestevez.gigirestaurants.data.models.Restaurant
import javax.inject.Inject

class SearchNearbyRestaurantsUseCase @Inject constructor(private val repository: RestaurantRepository) {

    suspend operator fun invoke(): Result<List<Restaurant>> = repository.searchNearbyRestaurants()
}