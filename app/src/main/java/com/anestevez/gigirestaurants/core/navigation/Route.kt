package com.anestevez.gigirestaurants.core.navigation

sealed class Route(val route: String) {
    object Search : Route("Search")
    object Bookmarks : Route("Bookmarks")
    object BookmarksDetail : Route("BookmarksDetail")
    object SearchDetail : Route("SearchDetail")

}