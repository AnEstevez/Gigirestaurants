package com.anestevez.gigirestaurants.ui.composables

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anestevez.gigirestaurants.R
import com.anestevez.gigirestaurants.ui.common.ItemUiState
import com.anestevez.gigirestaurants.ui.theme.ShimmerHighlightColor
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RestaurantListVerticalGrid(
    modifier: Modifier = Modifier,
    itemUiStates: List<ItemUiState>,
    onClick: (Int) -> Unit = {},
    contentPaddingValues: PaddingValues,
    columns: Int = 2,
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        LazyVerticalGrid(
            contentPadding = contentPaddingValues,
            columns = GridCells.Fixed(columns)
        ) {
            items(items = itemUiStates) { itemUiState ->
                RestaurantListItem(
                    Modifier
                        .semantics {
                            contentDescription = itemUiState.restaurant.name ?: "restaurant X"
                        }
                        .animateItemPlacement()
                        .fillMaxWidth()
                        .padding(2.dp),
                    itemUiState = itemUiState
                ) { onClick(itemUiState.restaurant.id!!) }
            }
        }

    }
}

@Composable
fun RestaurantListItem(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp),
    itemUiState: ItemUiState,
    onClick: () -> Unit = {},
) {

    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .wrapContentSize()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)) {
            Box {
                Thumb(itemUiState.restaurant.thumbUrl)
                Box(modifier = Modifier.align(Alignment.BottomEnd).padding(5.dp)) {
                    BookmarkButton(
                        coroutineScope,
                        itemUiState,
                    )
                }
            }
            Column(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()) {
                Row {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.restaurant),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Icon(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )

                }
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    textAlign = TextAlign.Start,
                    text = itemUiState.restaurant.name ?: "-",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}

@Composable
private fun Thumb(url: String?) {
    var placeholderVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = url,
            contentDescription = "thumb",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .placeholder(
                    visible = placeholderVisible,
                    placeholderFadeTransitionSpec = { tween(durationMillis = 2000) },
                    contentFadeTransitionSpec = { tween(durationMillis = 2000) },
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight.fade(highlightColor = ShimmerHighlightColor),
                ),
            contentScale = ContentScale.Crop,
            onLoading = { placeholderVisible = true },
            onSuccess = { placeholderVisible = false },
            onError = {
                placeholderVisible = false
            },
            error = rememberVectorPainterWithColor(
                image = ImageVector.vectorResource(R.drawable.ic_error),
                tintColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            fallback = rememberVectorPainterWithColor(
                image = ImageVector.vectorResource(R.drawable.ic_error),
                tintColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
        )

    }
}
