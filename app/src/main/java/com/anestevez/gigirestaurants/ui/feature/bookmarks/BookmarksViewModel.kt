package com.anestevez.gigirestaurants.ui.feature.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anestevez.gigirestaurants.core.toItemUiState
import com.anestevez.gigirestaurants.domain.GetBookmarkedRestaurantsUseCase
import com.anestevez.gigirestaurants.domain.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getBookmarkedRestaurantsUseCase: GetBookmarkedRestaurantsUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    var uiState = MutableStateFlow(BookmarksUiState())
        private set

    init {

        viewModelScope.launch {
            getBookmarkedRestaurantsUseCase().collect { result ->
                result.fold({ restaurants ->
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

    }

    fun dismissUserMessage() {
        uiState.update { uiState.value.copy(userMessage = "") }
    }


}