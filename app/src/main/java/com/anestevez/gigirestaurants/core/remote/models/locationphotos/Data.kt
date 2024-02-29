package com.anestevez.gigirestaurants.core.remote.models.locationphotos

data class Data(
    val album: String,
    val caption: String,
    val id: Int,
    val images: Images,
    val is_blessed: Boolean,
    val published_date: String,
    val source: Source,
    val user: User
)