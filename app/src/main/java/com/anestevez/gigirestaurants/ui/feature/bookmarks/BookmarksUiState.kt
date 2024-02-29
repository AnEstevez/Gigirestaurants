package com.anestevez.gigirestaurants.ui.feature.bookmarks

import com.anestevez.gigirestaurants.ui.common.ItemUiState

data class BookmarksUiState(
    val loading: Boolean = true,
    val data: List<ItemUiState> = emptyList(),
    val userMessage: String = ""
)