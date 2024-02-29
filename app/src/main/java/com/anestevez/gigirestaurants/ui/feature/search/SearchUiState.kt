package com.anestevez.gigirestaurants.ui.feature.search

import com.anestevez.gigirestaurants.ui.common.ItemUiState

data class SearchUiState(
    val loading: Boolean = true,
    val data: List<ItemUiState> = emptyList(),
    val userMessage: String = ""
)