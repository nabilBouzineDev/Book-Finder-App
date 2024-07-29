package com.nabilbdev.bookfinder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun BackButtonIcon(
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(35.dp)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            .clickable(onClick = onBackButtonClicked, role = Role.Button)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = Color.White,
            contentDescription = "Back",
            modifier = Modifier.fillMaxSize()
        )
    }
}