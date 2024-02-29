package com.anestevez.gigirestaurants.core.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RestaurantEntity::class], version = 1, exportSchema = false)
abstract class GigiDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

}