package com.anestevez.gigirestaurants.ui.feature.bookmarks.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.anestevez.gigirestaurants.ui.GigiAppState
import com.anestevez.gigirestaurants.ui.theme.GigirestaurantsTheme

@Composable
fun BookmarksDetailScreen(
    gigiAppState: GigiAppState,
) {
    BookmarksDetailContentScreen()
}

@Composable
private fun BookmarksDetailContentScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
    ) {
        BasicText("Bookmarks Detail Screen")


        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun BookmarksDetailContentScreenPreview() {
    GigirestaurantsTheme {
        BookmarksDetailContentScreen()
    }
}