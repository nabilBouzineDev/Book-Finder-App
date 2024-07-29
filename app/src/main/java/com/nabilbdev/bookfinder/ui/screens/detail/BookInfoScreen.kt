package com.nabilbdev.bookfinder.ui.screens.detail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nabilbdev.bookfinder.ui.components.ExpandableText

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BookInfoScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    bookId: String,
    image: String,
    title: String,
    publishedDate: String,
    category: String,
    description: String,
    previewLink: String,
    authors: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())

    ) {
        BookInfoContent(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            bookId = bookId,
            image = image,
            title = title,
            authors = authors,
            category = category,
            publishedDate = publishedDate
        )
        BookDescription(
            description = description
        )
        VisitBookButton(
            previewLink = previewLink
        )
    }
}

@Composable
fun VisitBookButton(
    previewLink: String,
    modifier: Modifier = Modifier
) {
    // context is needed to start activity
    val context = LocalContext.current

    Button(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .padding(
                bottom = 16.dp
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = { openBookPreview(context, previewLink) }
    ) {
        Text(
            text = "Visit Book",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 4.dp,
                start = 8.dp,
                end = 8.dp
            )
        )
    }
}

fun openBookPreview(context: Context, url: String) {
    try {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No browser app found", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun BookDescription(
    description: String,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        ExpandableText(
            text = description,
            maxLines = 3,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            paddingValues = PaddingValues(top = 16.dp)
        )
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BookInfoContent(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    bookId: String,
    image: String,
    title: String,
    publishedDate: String,
    category: String,
    authors: List<String>,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        BookImageCard(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            bookId = bookId,
            image = image,
            modifier = Modifier
                .zIndex(1f) // Make sure image above.
                .offset(y = 90.dp)
        )
        BookDetailsCard(
            title = title,
            authors = authors,
            publishedDate = publishedDate,
            category = category,
        )
    }
}