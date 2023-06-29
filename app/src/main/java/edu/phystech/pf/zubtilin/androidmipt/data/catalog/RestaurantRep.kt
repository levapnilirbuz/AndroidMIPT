package edu.phystech.pf.zubtilin.androidmipt.data.catalog

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao
) {

    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            val nearest = restaurantDao.getAllNearest()
            val popular = restaurantDao.getAllPopular()
            if (nearest.isNotEmpty() && popular.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = nearest.map { it.mapToRemoteRestaurant() },
                        popular = popular.map { it.mapToRemoteRestaurant() },
                        commercial = null
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()
                restaurantDao.insertAllNearest(*response.nearest.map {
                    it.mapToNearestRestaurantEntity()
                }.toTypedArray())
                restaurantDao.insertAllPopular(*response.popular.map {
                    it.mapToPopularRestaurantEntity()
                }.toTypedArray())

                emit(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}