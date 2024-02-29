package com.anestevez.gigirestaurants.ui.feature.bookmarks.detail

import com.anestevez.gigirestaurants.ui.common.ItemUiState

data class BookmarksDetailUiState(
    val loading: Boolean = true,
    val data: ItemUiState? = null,
    val userMessage: String = ""
)