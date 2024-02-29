package com.anestevez.gigirestaurants.core.location

interface LocationDataSource {
    suspend fun getLastLatLong(): String
}