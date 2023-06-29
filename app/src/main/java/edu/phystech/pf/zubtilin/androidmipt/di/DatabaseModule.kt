package edu.phystech.pf.zubtilin.androidmipt.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.RestaurantDao
import edu.phystech.pf.zubtilin.androidmipt.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "food_delivery"
    ).build()

    @Provides
    fun provideRestaurantsDao(appDatabase: AppDatabase): RestaurantDao =
        appDatabase.restaurantDao()
}