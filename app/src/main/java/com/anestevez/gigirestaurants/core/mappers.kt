package com.anestevez.gigirestaurants.core

import com.anestevez.gigirestaurants.core.remote.models.locationdetails.LocationDetailsResponse
import com.anestevez.gigirestaurants.core.remote.models.nearbysearch.Data
import com.anestevez.gigirestaurants.data.models.Restaurant
import com.anestevez.gigirestaurants.ui.common.ItemUiState
import timber.log.Timber

fun LocationDetailsResponse.toDomain(): Restaurant {
    var restaurant = Restaurant()
    try {
        restaurant = Restaurant(
            id = this.location_id.toInt(),
            name = this.name,
//            description = this.description ?: "-",
//            email = this.email ?: "-",
            description = "-",
            email =  "-",

            phone = this.phone ?: "-",
            rating = this.rating ?: "-",
        )
    } catch (t: Throwable) {
        Timber.e(t)
    }
    return restaurant
}


fun Data.toDomain(): Restaurant =
    Restaurant(
        id = this.location_id.toInt(),
        name = this.name,
        rating = this.rating ?: "-",
    )

fun com.anestevez.gigirestaurants.core.remote.models.search.Data.toDomain() =
    Restaurant(
        id = this.location_id.toInt(),
        name = this.name,
        rating = this.rating ?: "-",
    )

fun Restaurant.toItemUiState(onBookmark: suspend () -> Unit = {}): ItemUiState {
    var itemUiState = ItemUiState(Restaurant())
    try {
        itemUiState = ItemUiState(
        restaurant = this
    ).apply { this.onBookmark = { onBookmark() } }
    } catch (t: Throwable) {
        Timber.e(t)
    }
    return itemUiState
}
