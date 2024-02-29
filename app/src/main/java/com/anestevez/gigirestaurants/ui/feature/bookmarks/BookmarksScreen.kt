package com.anestevez.gigirestaurants.ui.feature.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anestevez.gigirestaurants.data.models.Restaurant
import com.anestevez.gigirestaurants.ui.GigiAppState
import com.anestevez.gigirestaurants.ui.common.ItemUiState
import com.anestevez.gigirestaurants.ui.composables.RestaurantListVerticalGrid
import com.anestevez.gigirestaurants.ui.rememberGigiAppState
import com.anestevez.gigirestaurants.ui.theme.GigirestaurantsTheme
import com.commandiron.compose_loading.CubeGrid

@Composable
fun BookmarksScreen(
    gigiAppState: GigiAppState,
    viewModel: BookmarksViewModel = hiltViewModel(),
    onClick: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.userMessage.isNotEmpty()) {
        LaunchedEffect(uiState.userMessage) {
            gigiAppState.onShowUserMessage(uiState.userMessage)
            viewModel.dismissUserMessage()
        }
    }

    BookmarksContentScreen(
        uiState = uiState,
        contentPaddingValues = PaddingValues(
            top = gigiAppState.scaffoldPadding.value.calculateTopPadding(),
            start = 5.dp,
            end = 5.dp,
            bottom = gigiAppState.scaffoldPadding.value.calculateBottomPadding()
        ),
    ) { id -> onClick(id) }
}

@Composable
private fun BookmarksContentScreen(
    uiState: BookmarksUiState,
    contentPaddingValues: PaddingValues,
    onClick: (Int) -> Unit,
) {

    Column() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            RestaurantListVerticalGrid(
                itemUiStates = uiState.data,
                onClick = { characterId -> onClick(characterId) },
                contentPaddingValues = contentPaddingValues,
                columns = 2
            )
            if (uiState.loading) {
                CubeGrid(
                    modifier = Modifier.align(Alignment.Center),
                    durationMillis = 800,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }


}

@Preview
@Composable
private fun BookmarksContentScreenPreview() {
    val gigiAppState = rememberGigiAppState()
    val uiState = BookmarksUiState(
        data = listOf(
            ItemUiState(
                restaurant = Restaurant(
                    id = 777,
                    name = "The BEAR"
                )
            )
        )
    )
    GigirestaurantsTheme {
        BookmarksContentScreen(
            uiState = uiState,
            contentPaddingValues = PaddingValues(
                top = gigiAppState.scaffoldPadding.value.calculateTopPadding(),
                start = 5.dp,
                end = 5.dp,
                bottom = gigiAppState.scaffoldPadding.value.calculateBottomPadding()
            ),
        ) {}
    }
}