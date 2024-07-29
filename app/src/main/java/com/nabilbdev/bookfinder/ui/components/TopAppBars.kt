package com.nabilbdev.bookfinder.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nabilbdev.bookfinder.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndHomeScreenTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium
            )
        },
        modifier = modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopAppBar(
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BackButtonIcon(
                    onBackButtonClicked = onBackButtonClicked,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(0.25f)
                        .wrapContentSize(Alignment.CenterStart)
                )
                Text(
                    text = "Book Details",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(0.75f)
                )
            }
        }
    )
}