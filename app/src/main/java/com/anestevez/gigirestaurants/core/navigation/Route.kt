package com.anestevez.gigirestaurants.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument>
) {
    companion object {
        const val ID = "Id"
    }

    object Search : Route(route = "Search", arguments = emptyList())
    object Bookmarks : Route(route = "Bookmarks", arguments = emptyList())
    object BookmarksDetail : Route(
        route = "BookmarksDetail",
        arguments = listOf(
            navArgument(ID) { type = NavType.IntType }
        ),
    )

    object SearchDetail : Route(
        route = "SearchDetail",
        arguments = listOf(
            navArgument(ID) { type = NavType.IntType }
        ),
    )

}