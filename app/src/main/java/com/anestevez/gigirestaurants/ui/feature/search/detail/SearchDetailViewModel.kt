package com.anestevez.gigirestaurants.ui.feature.search.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anestevez.gigirestaurants.core.navigation.Route.Companion.ID
import com.anestevez.gigirestaurants.core.toItemUiState
import com.anestevez.gigirestaurants.domain.GetRestaurantByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val getRestaurantByIdUseCase: GetRestaurantByIdUseCase
) : ViewModel() {

    private val id = stateHandle.get<Int>(ID) ?: 0

    var uiState = MutableStateFlow(SearchDetailUiState())
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
//
//        viewModelScope.launch {
//            getRestaurantByIdUseCase(id = id).collect{result ->
//                result.fold({ restaurant ->
//                    uiState.update {
//                        uiState.value.copy(
//                            loading = false,
//                            data = restaurant.toItemUiState()
//                        )
//                    }
//                }) { throwable ->
//                    uiState.update {
//                        uiState.value.copy(
//                            loading = false,
//                            userMessage = throwable.message ?: "Something went wrong"
//                        )
//                    }
//                }
//            }
//        }
    }

    fun dismissUserMessage() {
        uiState.update { uiState.value.copy(userMessage = "") }
    }


}