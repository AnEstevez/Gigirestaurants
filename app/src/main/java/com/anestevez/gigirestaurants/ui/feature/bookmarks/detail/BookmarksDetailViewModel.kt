package com.anestevez.gigirestaurants.ui.feature.bookmarks.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anestevez.gigirestaurants.core.navigation.Route.Companion.ID
import com.anestevez.gigirestaurants.core.toItemUiState
import com.anestevez.gigirestaurants.domain.GetRestaurantByIdUseCase
import com.anestevez.gigirestaurants.domain.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksDetailViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val getRestaurantByIdUseCase: GetRestaurantByIdUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    private val id = stateHandle.get<Int>(ID) ?: 0

    var uiState = MutableStateFlow(BookmarksDetailUiState())
        private set

    init {
        viewModelScope.launch {
            getRestaurantByIdUseCase(id = id).collect { result ->
                uiState.update { currentState ->
                    result.fold({ restaurant ->
                        currentState.copy(
                            loading = false,
                            data = restaurant.toItemUiState()
                        )

                    }) { throwable ->
                        currentState.copy(
                            loading = false,
                            userMessage = throwable.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }

    }

    fun onBookmarked() {
        viewModelScope.launch {
            uiState.value.data?.let { toggleBookmarkUseCase(it.restaurant) }
            val restaurant = uiState.value.data?.restaurant?.copy(bookmarked = !uiState.value.data?.restaurant?.bookmarked!!)
            uiState.update { uiState.value.copy(data = uiState.value.data?.copy(restaurant = restaurant!!)) }
        }
    }
    fun dismissUserMessage() {
        uiState.update { uiState.value.copy(userMessage = "") }
    }


}