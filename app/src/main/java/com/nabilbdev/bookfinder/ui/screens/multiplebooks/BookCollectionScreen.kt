package com.nabilbdev.bookfinder.ui.screens.multiplebooks

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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nabilbdev.bookfinder.model.BookResponse
import com.nabilbdev.bookfinder.model.Item
import com.nabilbdev.bookfinder.model.VolumeInfo
import com.nabilbdev.bookfinder.ui.theme.BookFinderTheme

@Composable
fun BookCollectionScreen(
    numberOfBooks: Int,
    bookItems: BookResponse,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 144.dp, bottom = 16.dp)
    ) {
        AboutBooks(
            numberOfBooks = numberOfBooks,
        )
        BookListContent(
            books = bookItems.items,
        )
    }
}

@Composable
fun BookListContent(
    books: List<Item>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 6.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Chunk list into a row list contains 5 books
        items(books.chunked(5)) { rowBooks ->
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Iterate over each single book in the row list.
                items(rowBooks) { book ->
                    BookThumbnail(
                        thumbnail = book.volumeInfo.imageLinks.thumbnail
                    )
                }
            }
        }
    }
}

@Composable
fun BookThumbnail(
    thumbnail: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(100.dp)
            .aspectRatio(9f / 16f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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


@Preview(showSystemUi = true)
@Composable
fun BookInfoContentPreview() {
    BookFinderTheme {
        BookCollectionScreen(
            numberOfBooks = 255,
            bookItems = BookResponse(
                listOf(
                    Item("0", VolumeInfo()),
                    Item("1", VolumeInfo()),
                    Item("2", VolumeInfo()),
                    Item("3", VolumeInfo()),
                    Item("4", VolumeInfo()),
                    Item("5", VolumeInfo()),
                    Item("6", VolumeInfo()),
                    Item("7", VolumeInfo()),
                    Item("8", VolumeInfo()),
                    Item("9", VolumeInfo()),
                    Item("9", VolumeInfo()),
                ), "_", 255
            )
        )
    }
}