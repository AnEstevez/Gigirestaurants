package com.anestevez.gigirestaurants.core.remote.models.locationdetails

data class LocationDetailsResponse(
    val address_obj: AddressObj,
    val ancestors: List<Ancestor>,
    val awards: List<Any>,
    val category: Category,
    val cuisine: List<Cuisine>,
//    val description: String?,
//    val email: String?,
    val features: List<String>,
    val hours: Hours,
    val latitude: String,
    val location_id: String,
    val longitude: String,
    val name: String,
    val neighborhood_info: List<NeighborhoodInfo>,
    val num_reviews: String,
    val phone: String?,
    val photo_count: String,
    val price_level: String,
    val ranking_data: RankingData,
    val rating: String?,
    val rating_image_url: String,
    val review_rating_count: ReviewRatingCount,
    val see_all_photos: String,
    val subcategory: List<Any>,
    val subratings: Subratings,
    val timezone: String,
    val trip_types: List<TripType>,
    val web_url: String,
    val website: String,
    val write_review: String
)