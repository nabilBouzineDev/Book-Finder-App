package com.nabilbdev.bookfinder.ui.screens.bookinfo

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nabilbdev.bookfinder.ui.theme.BookFinderTheme

@Composable
fun BookInfoScreen(
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
        /** UI Plan
         * Column ✔
         *   Box ✔
         *       Card Image ✔
         *       Column ✔
         *           Title ✔
         *           Row ✔
         *               Rating ✔
         *           Card Category ✔
         *   Column ✔
         *       Description Label ✔
         *       Text ✔
         *   Button ✔
         */
        BookInfoContent(
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
        onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(previewLink)
            )
            context.startActivity(intent)
        }
    ) {
        Text(
            text = "Visit Book",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun BookInfoContent(
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
            image = image,
            modifier = Modifier
                .padding(top = 8.dp)
                .zIndex(1f) // Make sure image above.
                .offset(y = 100.dp)
        )
        BookDetailsCard(
            title = title,
            authors = authors,
            publishedDate = publishedDate,
            category = category,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun BookInfoContentPreview() {
    BookFinderTheme {
        BookInfoScreen(
            image = "Image here",
            title = """
                Book title longer longer longer longer longer longer longer longer longer longer 
                Book title longer longer longer longer longer longer longer longer longer longer
                Book title longer longer longer longer longer longer longer longer longer longer
            """.trimIndent(),
            authors = listOf("Author 1"),
            category = "Science",
            description = "This is some sort of mock unreal fake description of this fake book",
            previewLink = "https://www.google.com",
            publishedDate = "2020"
        )
    }
}
