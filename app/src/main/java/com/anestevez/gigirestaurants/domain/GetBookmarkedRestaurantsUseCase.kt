package com.anestevez.gigirestaurants.domain

import com.anestevez.gigirestaurants.data.RestaurantRepository
import com.anestevez.gigirestaurants.data.models.Restaurant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedRestaurantsUseCase @Inject constructor(private val repository: RestaurantRepository) {

    operator fun invoke(): Flow<Result<List<Restaurant>>> = repository.getBookmarkedRestaurants()
}