package com.anestevez.gigirestaurants.ui.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anestevez.gigirestaurants.core.toItemUiState
import com.anestevez.gigirestaurants.domain.SearchNearbyRestaurantsUseCase
import com.anestevez.gigirestaurants.domain.SearchRestaurantsByNameUseCase
import com.anestevez.gigirestaurants.domain.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRestaurantsByNameUseCase: SearchRestaurantsByNameUseCase,
    private val searchNearbyRestaurantsUseCase: SearchNearbyRestaurantsUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    var uiState = MutableStateFlow(SearchUiState())
        private set

    init {

        viewModelScope.launch {
            searchNearbyRestaurantsUseCase().fold({ restaurants ->
                uiState.update {
                    uiState.value.copy(
                        loading = false,
                        data = restaurants.map {
                            it.toItemUiState().apply {
                                onBookmark =
                                    { toggleBookmarkUseCase(restaurant = this.restaurant) }
                            }
                        }
                    )
                }
            }) { throwable ->
                uiState.update {
                    uiState.value.copy(
                        loading = false,
                        userMessage = throwable.message ?: "Something went wrong"
                    )
                }
            }
        }

    }

    fun searchByName(name: String) {
        uiState.update { uiState.value.copy(loading = true) }
        viewModelScope.launch {
            searchRestaurantsByNameUseCase(name = name).fold({ restaurants ->
                uiState.update {
                    uiState.value.copy(
                        loading = false,
                        data = restaurants.map { it.toItemUiState() }
                    )
                }
            }) { throwable ->
                uiState.update {
                    uiState.value.copy(
                        loading = false,
                        userMessage = throwable.message ?: "Something went wrong"
                    )
                }
            }
        }
    }

    fun dismissUserMessage() {
        uiState.update { uiState.value.copy(userMessage = "") }
    }


}