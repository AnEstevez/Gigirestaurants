package com.anestevez.gigirestaurants.ui.feature.search.detail

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.anestevez.gigirestaurants.R
import com.anestevez.gigirestaurants.ui.GigiAppState
import com.anestevez.gigirestaurants.ui.composables.BookmarkButton
import com.anestevez.gigirestaurants.ui.composables.rememberVectorPainterWithColor
import com.anestevez.gigirestaurants.ui.theme.GigirestaurantsTheme
import com.anestevez.gigirestaurants.ui.theme.ShimmerHighlightColor
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun SearchDetailScreen(
    gigiAppState: GigiAppState,
    viewModel: SearchDetailViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.userMessage.isNotEmpty()) {
        LaunchedEffect(uiState.userMessage) {
            gigiAppState.onShowUserMessage(uiState.userMessage)
            viewModel.dismissUserMessage()
        }
    }

    SearchDetailContentScreen(uiState = uiState)
}

@Composable
private fun SearchDetailContentScreen(
    uiState: SearchDetailUiState
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box {
            Thumb(url = uiState.data?.restaurant?.thumbUrl)
            if  (uiState.data != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp)
                ) {
                    BookmarkButton(
                        coroutineScope,
                        uiState.data,
                    )
                }
            }
        }
        Column(modifier = Modifier.padding(10.dp)) {
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
                textAlign = TextAlign.Start,
                text = uiState.data?.restaurant?.name ?: " - ",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.description),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }
            Text(
                textAlign = TextAlign.Start,
                text = uiState.data?.restaurant?.description ?: " - ",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.email),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }
            Text(
                textAlign = TextAlign.Start,
                text = uiState.data?.restaurant?.email ?: " - ",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.phone),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }
            Text(
                textAlign = TextAlign.Start,
                text = uiState.data?.restaurant?.phone ?: " - ",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.rating),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium
                )

                Icon(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }
            Text(
                textAlign = TextAlign.Start,
                text = uiState.data?.restaurant?.rating ?: " - ",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
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

@Preview
@Composable
private fun SearchDetailContentScreenPreview() {
    GigirestaurantsTheme {
        SearchDetailContentScreen(SearchDetailUiState())
    }
}