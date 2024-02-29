package com.anestevez.gigirestaurants.core.remote.models.nearbysearch

data class AddressObj(
    val address_string: String,
    val city: String,
    val country: String,
    val postalcode: String,
    val street1: String,
    val street2: String
)