package com.anestevez.gigirestaurants.ui.composables

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.anestevez.gigirestaurants.core.navigation.Route

@Composable
fun GigiNavigationBar(
    navItem: List<NavItem>,
    currentRoute: String,
    onClick: (String) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        tonalElevation = 0.dp,
    ) {
        navItem.forEach {
            NavigationBarItem(
                selected = currentRoute == it.item.route,
                onClick = { onClick(it.item.route) },
                icon = { Icon(imageVector = it.icon, contentDescription = it.name) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
            )
        }
    }
}

enum class NavItem(
    val item: Route,
    val icon: ImageVector,
) {
    SEARCH(Route.Search, Icons.Outlined.Search),
    FAVORITES(Route.Bookmarks, Icons.Outlined.Favorite),
}