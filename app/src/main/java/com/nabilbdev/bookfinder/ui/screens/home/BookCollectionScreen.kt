package com.nabilbdev.bookfinder.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nabilbdev.bookfinder.data.remote.model.Item

@Composable
fun BookCollectionScreen(
    onBookCardClick: (String) -> Unit,
    numberOfBooks: Int,
    bookItems: LazyPagingItems<Item>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        AboutBooks(
            numberOfBooks = numberOfBooks,
        )
        BookListContent(
            onBookCardClick = onBookCardClick,
            books = bookItems,
        )
    }
}

@Composable
fun BookListContent(
    onBookCardClick: (String) -> Unit,
    books: LazyPagingItems<Item>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Divide the items into groups of 10
        items(books.itemCount / 10) { rowIndex ->
            val startIndex = rowIndex * 10
            val endIndex = minOf(startIndex + 10, books.itemCount)

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp
                )
            ) {
                items(endIndex - startIndex) { indexWithinRow ->
                    val bookIndex = startIndex + indexWithinRow
                    val book = books[bookIndex]
                    if (book != null) {
                        BookThumbnail(
                            onBookCardClick = { onBookCardClick(book.id) },
                            thumbnail = book.volumeInfo.imageLinks.thumbnail
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookThumbnail(
    onBookCardClick: () -> Unit,
    thumbnail: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(100.dp)
            .aspectRatio(9f / 16f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onBookCardClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun AboutBooks(
    numberOfBooks: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "About $numberOfBooks Books ...",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier.padding(16.dp)
    )
}