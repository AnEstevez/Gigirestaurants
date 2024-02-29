package com.anestevez.gigirestaurants.ui.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anestevez.gigirestaurants.R
import com.anestevez.gigirestaurants.data.models.Restaurant
import com.anestevez.gigirestaurants.ui.GigiAppState
import com.anestevez.gigirestaurants.ui.common.ItemUiState
import com.anestevez.gigirestaurants.ui.composables.GigiSearchBar
import com.anestevez.gigirestaurants.ui.composables.RestaurantListVerticalGrid
import com.anestevez.gigirestaurants.ui.rememberGigiAppState
import com.anestevez.gigirestaurants.ui.theme.GigirestaurantsTheme
import com.commandiron.compose_loading.CubeGrid
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SearchScreen(
    gigiAppState: GigiAppState,
    viewModel: SearchViewModel = hiltViewModel(),
    onClick: (Int) -> Unit,
) {

    // permission state
    val permissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    if (permissionState.status.isGranted) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        if (uiState.userMessage.isNotEmpty()) {
            LaunchedEffect(uiState.userMessage) {
                gigiAppState.onShowUserMessage(uiState.userMessage)
                viewModel.dismissUserMessage()
            }
        }

        SearchScreenContent(
            uiState = uiState,
            contentPaddingValues = PaddingValues(
                top = gigiAppState.scaffoldPadding.value.calculateTopPadding(),
                start = 5.dp,
                end = 5.dp,
                bottom = gigiAppState.scaffoldPadding.value.calculateBottomPadding()
            ),
            onSearch = { name -> viewModel.searchByName(name) }
        ) { id -> onClick(id) }

    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val textToShow = if (permissionState.status.shouldShowRationale) {
                // If the user has denied the permission but the rationale can be shown,
                // then gently explain why the app requires this permission
                stringResource(id = R.string.permission_required)
            } else {
                // If it's the first time the user lands on this feature, or the user
                // doesn't want to be asked again for this permission, explain that the
                // permission is required
                stringResource(id = R.string.permission_required)
            }
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = textToShow,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(id = R.string.please_grant_permission),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Button(onClick = { permissionState.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
    }

}

@Composable
private fun SearchScreenContent(
    uiState: SearchUiState,
    contentPaddingValues: PaddingValues,
    onSearch: (String) -> Unit,
    onClick: (Int) -> Unit,
) {

    Column() {
        GigiSearchBar { name -> onSearch(name) }
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
private fun SearchScreenContentPreview() {

    val gigiAppState = rememberGigiAppState()
    val uiState = SearchUiState(
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
        SearchScreenContent(
            uiState = uiState,
            contentPaddingValues = PaddingValues(
                top = gigiAppState.scaffoldPadding.value.calculateTopPadding(),
                start = 5.dp,
                end = 5.dp,
                bottom = gigiAppState.scaffoldPadding.value.calculateBottomPadding()
            ),
            onSearch = {}
        ) {}
    }
}