package com.anestevez.gigirestaurants.domain

import com.anestevez.gigirestaurants.data.RestaurantRepository
import com.anestevez.gigirestaurants.data.models.Restaurant
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(private val repository: RestaurantRepository) {

    suspend operator fun invoke(restaurant: Restaurant) =
        repository.updateRestaurant(restaurant.copy(bookmarked = !restaurant.bookmarked))
}