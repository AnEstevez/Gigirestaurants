package com.anestevez.gigirestaurants.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anestevez.gigirestaurants.ui.composables.NavItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberGigiAppState(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navigationBarsInsetsDp: MutableState<Dp> = remember { mutableStateOf(0.dp) },
    scaffoldPadding: MutableState<PaddingValues> = remember { mutableStateOf(PaddingValues()) },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): GigiAppState =
    remember(
        navController,
        snackbarHostState,
        navigationBarsInsetsDp,
        scaffoldPadding,
        coroutineScope,
    ) {
        GigiAppState(
            navController,
            snackbarHostState,
            navigationBarsInsetsDp,
            scaffoldPadding,
            coroutineScope,
        )
    }

class GigiAppState(
    val navHostController: NavHostController,
    val snackbarHostState: SnackbarHostState,
    val navigationBarsInsetsDp: MutableState<Dp>,
    var scaffoldPadding: MutableState<PaddingValues>,
    val coroutineScope: CoroutineScope,

    ) {

    val topLevelDestinations = listOf(NavItem.SEARCH, NavItem.FAVORITES)

    val showBottomNavigationBar: Boolean
        @Composable get() = currentRoute in topLevelDestinations.map { it.item.route }

    val onShowUserMessage: (String) -> Unit
        get() = {
            coroutineScope.launch {
                this@GigiAppState.snackbarHostState.showSnackbar(
                    message = it,
                    duration = SnackbarDuration.Short
                )
            }
        }

    val currentRoute : String
        @Composable get() = navHostController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    fun popUp() {
        navHostController.popBackStack()
    }

    fun navigate(route: String) {
        navHostController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}
