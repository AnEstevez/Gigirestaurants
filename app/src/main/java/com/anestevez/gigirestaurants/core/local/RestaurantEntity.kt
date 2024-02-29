package com.anestevez.gigirestaurants.core.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    val name: String = "-",
    val description: String = "-",
    val email: String = "-",
    val phone: String = "-",
    val rating: String = "-",
    val thumbUrl: String? = null,
    @ColumnInfo(name = "bookmarked", defaultValue = "0")
    var bookmarked: Boolean = false,
)
