package com.anestevez.gigirestaurants.domain

import com.anestevez.gigirestaurants.data.RestaurantRepository
import com.anestevez.gigirestaurants.data.models.Restaurant
import javax.inject.Inject

class SearchRestaurantsByNameUseCase @Inject constructor(private val repository: RestaurantRepository) {

    suspend operator fun invoke(name: String): Result<List<Restaurant>> =
        repository.searchRestaurantsByName(name = name)
}