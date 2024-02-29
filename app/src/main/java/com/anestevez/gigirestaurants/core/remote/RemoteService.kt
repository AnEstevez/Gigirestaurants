package com.anestevez.gigirestaurants.core.remote

import com.anestevez.gigirestaurants.core.remote.models.locationdetails.LocationDetailsResponse
import com.anestevez.gigirestaurants.core.remote.models.locationphotos.LocationPhotosResponse
import com.anestevez.gigirestaurants.core.remote.models.nearbysearch.NearbySearchResponse
import com.anestevez.gigirestaurants.core.remote.models.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

    @GET("nearby_search")
    suspend fun searchNearbyRestaurants(
        @Query("latLong") latLong: String,
        @Query("key") apiKey: String,
        @Query("category") category: String = "restaurants",
        @Query("radius") radius: String = "1",
        @Query("radiusUnit") radiusUnit: String = "km",
        @Query("language") language: String = "en",
        ): NearbySearchResponse

    @GET("search")
    suspend fun searchRestaurantsByName(
        @Query("key") apiKey: String,
        @Query("searchQuery") searchQuery: String,
        @Query("category") category: String = "restaurants",
        @Query("radius") radius: String = "1",
        @Query("radiusUnit") radiusUnit: String = "km",
        @Query("language") language: String = "en",
    ): SearchResponse

    @GET("{id}/details")
    suspend fun getRestaurantById(
        @Path("id") id: Int,
        @Query("key") apiKey: String,
        @Query("language") language: String = "en",
        @Query("currency") currency: String = "EUR",
    ): LocationDetailsResponse

    @GET("{id}/photos")
    suspend fun getRestaurantThumbById(
        @Path("id") id: Int,
        @Query("key") apiKey: String,
        @Query("language") language: String = "en",
        @Query("limit") limit: Int = 1,
    ): LocationPhotosResponse

}