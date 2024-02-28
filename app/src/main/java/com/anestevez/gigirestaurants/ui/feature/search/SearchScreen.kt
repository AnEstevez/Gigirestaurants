package com.anestevez.gigirestaurants.ui.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.anestevez.gigirestaurants.ui.GigiAppState
import com.anestevez.gigirestaurants.ui.theme.GigirestaurantsTheme

@Composable
fun SearchScreen(
    gigiAppState: GigiAppState,
) {
    SearchScreenContent()
}

@Composable
private fun SearchScreenContent() {
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
        BasicText("Search Screen")


        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun SearchScreenContentPreview() {
    GigirestaurantsTheme {
        SearchScreenContent()
    }
}