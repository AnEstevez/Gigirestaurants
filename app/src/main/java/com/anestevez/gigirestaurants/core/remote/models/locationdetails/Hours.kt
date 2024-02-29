package com.anestevez.gigirestaurants.core.remote.models.locationdetails

data class Hours(
    val periods: List<Period>,
    val weekday_text: List<String>
)