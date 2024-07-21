package com.nabilbdev.bookfinder.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(
    text: String,
    maxLines: Int,
    textAlign: TextAlign,
    style: TextStyle,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal
) {
    var isExpanded by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .wrapContentHeight(Alignment.Bottom)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                hasOverflow = textLayoutResult.hasVisualOverflow
            },
            textAlign = textAlign,
            fontWeight = fontWeight,
            softWrap = true,
            color = MaterialTheme.colorScheme.secondary,
            style = style,
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