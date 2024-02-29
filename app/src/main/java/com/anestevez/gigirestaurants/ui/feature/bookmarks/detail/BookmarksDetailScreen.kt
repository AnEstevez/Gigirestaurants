package com.anestevez.gigirestaurants.ui.feature.bookmarks.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anestevez.gigirestaurants.R
import com.anestevez.gigirestaurants.ui.GigiAppState
import com.anestevez.gigirestaurants.ui.composables.DetailBookmarkButton
import com.anestevez.gigirestaurants.ui.composables.ThumbDetail
import com.anestevez.gigirestaurants.ui.theme.GigirestaurantsTheme

@Composable
fun BookmarksDetailScreen(
    gigiAppState: GigiAppState,
    viewModel: BookmarksDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.userMessage.isNotEmpty()) {
        LaunchedEffect(uiState.userMessage) {
            gigiAppState.onShowUserMessage(uiState.userMessage)
            viewModel.dismissUserMessage()
        }
    }

    BookmarksDetailContentScreen(
        uiState = uiState,
        onBookmarked = { viewModel.onBookmarked() }
    )
}

@Composable
private fun BookmarksDetailContentScreen(
    uiState: BookmarksDetailUiState,
    onBookmarked: () -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box {
            ThumbDetail(url = uiState.data?.restaurant?.thumbUrl)
            if (uiState.data != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp)
                ) {
                    DetailBookmarkButton(
                        coroutineScope = coroutineScope,
                        state = uiState.data,
                        onBookmark = onBookmarked,
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

@Preview
@Composable
private fun BookmarksDetailContentScreenPreview() {
    GigirestaurantsTheme {
        BookmarksDetailContentScreen(BookmarksDetailUiState()){}
    }
}