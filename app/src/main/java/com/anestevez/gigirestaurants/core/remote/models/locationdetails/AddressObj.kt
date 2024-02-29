package com.anestevez.gigirestaurants.core.remote.models.locationdetails

data class AddressObj(
    val address_string: String,
    val city: String,
    val country: String,
    val postalcode: String,
    val street1: String
)