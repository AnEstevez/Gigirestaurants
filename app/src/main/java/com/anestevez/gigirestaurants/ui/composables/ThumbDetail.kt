package com.anestevez.gigirestaurants.ui.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import coil.compose.AsyncImage
import com.anestevez.gigirestaurants.R
import com.anestevez.gigirestaurants.ui.theme.ShimmerHighlightColor
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun ThumbDetail(url: String?) {
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