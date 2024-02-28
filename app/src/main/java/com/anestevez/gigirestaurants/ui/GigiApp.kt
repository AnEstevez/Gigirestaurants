package com.anestevez.gigirestaurants.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anestevez.gigirestaurants.ui.composables.GigiNavigationBar

@Composable
fun GigiApp(
    gigiAppState: GigiAppState = rememberGigiAppState()
) {

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = gigiAppState.snackbarHostState,
                modifier = Modifier.padding(gigiAppState.scaffoldPadding.value)
            )
        },
        bottomBar = {
            if (gigiAppState.showBottomNavigationBar) {
                GigiNavigationBar(
                    navItem = gigiAppState.topLevelDestinations,
                    currentRoute = gigiAppState.currentRoute,
                    onClick = { gigiAppState.navigate(it) })
            }
        }
    ) { padding ->
        gigiAppState.navigationBarsInsetsDp.value =
            WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        gigiAppState.scaffoldPadding.value = padding
        Navigation(gigiAppState)
    }
}