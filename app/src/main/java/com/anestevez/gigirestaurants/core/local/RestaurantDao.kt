package com.anestevez.gigirestaurants.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant WHERE id = :id")
    suspend fun getRestaurantById(id: Int): RestaurantEntity

    @Query("Select * FROM restaurant WHERE bookmarked = 1 order by name asc")
    fun getFavorites(): Flow<List<RestaurantEntity>>

    @Update
    suspend fun updateRestaurant(restaurant: RestaurantEntity)

    @Insert(onConflict = IGNORE)
    suspend fun insertRestaurantsList(restaurants: List<RestaurantEntity>)

}