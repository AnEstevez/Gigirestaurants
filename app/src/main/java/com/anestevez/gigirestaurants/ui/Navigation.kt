package com.anestevez.gigirestaurants.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anestevez.gigirestaurants.core.navigation.Route
import com.anestevez.gigirestaurants.ui.feature.bookmarks.BookmarksScreen
import com.anestevez.gigirestaurants.ui.feature.search.SearchScreen
import com.anestevez.gigirestaurants.ui.feature.search.detail.SearchDetailScreen

@Composable
fun Navigation(
    gigiAppState: GigiAppState,
) {

    NavHost(
        navController = gigiAppState.navHostController,
        startDestination = Route.Search.route
    ) {
        composable(Route.Search.route) {
            SearchScreen(gigiAppState = gigiAppState)
        }
        composable(Route.Bookmarks.route) {
            BackHandler(onBack = { gigiAppState.clearAndNavigate(Route.Search.route) })
            BookmarksScreen(gigiAppState = gigiAppState)
        }
        composable(Route.BookmarksDetail.route) {
            SearchDetailScreen(gigiAppState = gigiAppState)
        }
        composable(Route.SearchDetail.route) {
            BookmarksScreen(gigiAppState = gigiAppState)
        }
    }
}


