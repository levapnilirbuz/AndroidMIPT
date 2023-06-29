package edu.phystech.pf.zubtilin.androidmipt.screens.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.RemoteRestaurant
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.RestaurantRepository
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.mapToNearestRestaurantEntity
import edu.phystech.pf.zubtilin.androidmipt.data.catalog.mapToPopularRestaurantEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

data class RestaurantState(
    val search: String = "",
    val nearestRestaurants: List<RemoteRestaurant> = emptyList(),
    val popularRestaurants: List<RemoteRestaurant> = emptyList()
)


@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
) :
    ViewModel() {

    private val _viewState: MutableLiveData<RestaurantState> = MutableLiveData(RestaurantState())
    val viewState: LiveData<RestaurantState> = _viewState

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch(Dispatchers.Default) {
            restaurantRepository.fetchCatalog()
                .collectLatest { response ->
                    _viewState.postValue(
                        _viewState.value?.copy(
                            nearestRestaurants = response.nearest,
                            popularRestaurants = response.popular
                        )
                    )
                }
        }
    }
}