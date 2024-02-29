package com.anestevez.gigirestaurants.core.remote.models.locationphotos

data class Images(
    val large: Large,
    val medium: Medium,
    val original: Original,
    val small: Small,
    val thumbnail: Thumbnail
)