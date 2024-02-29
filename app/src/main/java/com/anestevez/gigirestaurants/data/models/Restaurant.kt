package com.anestevez.gigirestaurants.data.models

data class Restaurant(
    val id: Int? = null,
    val name: String? = null,
    val description: String = "-",
    val email: String = "-",
    val phone: String = "-",
    val rating: String = "-",
    val thumbUrl: String? = null,
    var bookmarked: Boolean = false,
)
