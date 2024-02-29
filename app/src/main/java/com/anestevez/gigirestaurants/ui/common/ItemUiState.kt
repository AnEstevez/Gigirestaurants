package com.anestevez.gigirestaurants.ui.common

import com.anestevez.gigirestaurants.data.models.Restaurant

data class ItemUiState(val restaurant: Restaurant) {
    var onBookmark: suspend () -> Unit = {}
}
