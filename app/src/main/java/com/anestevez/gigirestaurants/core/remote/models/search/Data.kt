package com.anestevez.gigirestaurants.core.remote.models.search

data class Data(
    val address_obj: AddressObj,
    val bearing: String,
    val distance: String,
    val rating: String?,
    val location_id: String,
    val name: String
)