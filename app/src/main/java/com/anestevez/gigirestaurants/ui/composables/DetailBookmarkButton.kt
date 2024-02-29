package com.anestevez.gigirestaurants.ui.composables

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.anestevez.gigirestaurants.ui.common.ItemUiState
import kotlinx.coroutines.CoroutineScope

@Composable
fun DetailBookmarkButton(
    coroutineScope: CoroutineScope,
    state: ItemUiState,
    onBookmark: () -> Unit,
) {
    FilledIconButton(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(25),
                spotColor = MaterialTheme.colorScheme.onSurface
            )
            .size(35.dp),
        onClick = { onBookmark() },
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(25)
    ) {
        Crossfade(
            targetState = state.restaurant.bookmarked,
            animationSpec = tween(durationMillis = 1000), label = "bookmarked animation"
        ) { bookmarked ->
            if (bookmarked) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "filled favorite icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "border favorite icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}