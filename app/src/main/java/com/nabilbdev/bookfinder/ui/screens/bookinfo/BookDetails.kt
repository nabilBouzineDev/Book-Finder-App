package com.nabilbdev.bookfinder.ui.screens.bookinfo

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun ExpandedBookTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 94.dp,
                bottom = 8.dp,
                start = 8.dp,
                end = 8.dp
            )
            .wrapContentHeight(Alignment.Bottom)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                hasOverflow = textLayoutResult.hasVisualOverflow
            },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            softWrap = true,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(1f)
        )
        // Show [See more] if text has overflow
        // Show [See less] if text is expanded
        if (hasOverflow || isExpanded) {
            Text(
                text = if (isExpanded) "See less" else "See more",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                softWrap = true,
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .clickable {
                        isExpanded = !isExpanded
                    }
                    .padding(start = 2.dp)
                    .align(Alignment.Bottom)
            )
        }
    }
}


@Composable
fun BookPublishedDateAndAuthor(
    authors: List<String>,
    publishedDate: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                end = 16.dp,
                start = 32.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BookPublishedDate(
            publishedDate = publishedDate,
        )
        BookAuthor(
            authors = authors,
            modifier = Modifier
                .padding(start = 16.dp)
        )
    }
}

@Composable
fun BookPublishedDate(
    publishedDate: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(22.dp),
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = publishedDate.substringBefore("-"),
            softWrap = true,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BookAuthor(
    authors: List<String>,
    modifier: Modifier = Modifier
) {
    // Restrict the number of authors to 3
    val mainAuthors = if (authors.size > 3) {
        authors.subList(0, 3)
    } else {
        authors
    }

    // Build a comma-separated list of authors
    val authorsList = buildString {
        when (mainAuthors.size) {
            1 -> append(mainAuthors[0])
            2 -> {
                append(mainAuthors[0])
                append(" and ")
                append(mainAuthors[1])
            }

            else -> {
                append(mainAuthors[0])
                append(", ")
                append(mainAuthors[1])
                append(" and ")
                append(mainAuthors[2])
            }
        }
    }
    Text(
        modifier = modifier,
        text = "by $authorsList",
        softWrap = true,
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.bodySmall,
    )
}