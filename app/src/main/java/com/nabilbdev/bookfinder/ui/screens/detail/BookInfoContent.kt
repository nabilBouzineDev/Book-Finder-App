package com.nabilbdev.bookfinder.ui.screens.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nabilbdev.bookfinder.ui.theme.card
import com.nabilbdev.bookfinder.ui.theme.darkCard


@Composable
fun BookDetailsCard(
    title: String,
    publishedDate: String,
    category: String,
    authors: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
            )
            .background(
                color = when {
                    isSystemInDarkTheme() -> darkCard
                    else -> card
                },
                shape = CardDefaults.elevatedShape
            )
    ) {
        ExpandedBookTitle(
            title = title
        )
        BookPublishedDateAndAuthor(
            authors = authors,
            publishedDate = publishedDate
        )
        CategoryCard(
            category = category
        )
    }
}

@Composable
fun CategoryCard(
    category: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(100),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.15f)
        ),
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = category,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 4.dp,
                start = 12.dp,
                end = 12.dp
            )
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BookImageCard(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    bookId: String,
    image: String,
    modifier: Modifier = Modifier
) {
    with(sharedTransitionScope) {
        Card(
            modifier = modifier
                .width(150.dp)
                .aspectRatio(9f / 16f),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .sharedElement(
                            state = sharedTransitionScope.rememberSharedContentState(key = "thumbnail/${bookId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .fillMaxSize()
                )
            }
        }
    }
}
