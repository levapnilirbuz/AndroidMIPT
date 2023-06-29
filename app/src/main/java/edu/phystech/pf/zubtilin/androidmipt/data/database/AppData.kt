package edu.phystech.pf.zubtilin.androidmipt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.NearestRestaurantEntity
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.PopularRestaurantEntity
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.RestaurantDao


@Database(entities = [NearestRestaurantEntity::class, PopularRestaurantEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}