package com.anestevez.gigirestaurants.core.remote.models.locationdetails

data class RankingData(
    val geo_location_id: String,
    val geo_location_name: String,
    val ranking: String,
    val ranking_out_of: String,
    val ranking_string: String
)