package com.anestevez.gigirestaurants.ui.feature.search.detail

import com.anestevez.gigirestaurants.ui.common.ItemUiState

data class SearchDetailUiState(
    val loading: Boolean = true,
    val data: ItemUiState? = null,
    val userMessage: String = ""
)