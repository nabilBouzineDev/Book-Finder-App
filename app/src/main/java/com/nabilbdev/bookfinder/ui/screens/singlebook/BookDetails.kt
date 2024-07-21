package com.nabilbdev.bookfinder.ui.screens.singlebook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nabilbdev.bookfinder.ui.components.ExpandableText


@Composable
fun ExpandedBookTitle(
    title: String,
) {
    ExpandableText(
        text = title,
        maxLines = 1,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
        paddingValues = PaddingValues(
            top = 94.dp,
            bottom = 8.dp,
            start = 8.dp,
            end = 8.dp
        ),
    )
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