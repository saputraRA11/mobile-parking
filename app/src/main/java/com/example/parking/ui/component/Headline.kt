package com.example.parking.ui.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HeadLineUser(modifier: Modifier) {
    ImageParkName(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(0.8f)
        ,
    )
    ProfileImage(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(1f)
    )
}

@Composable
fun HeadLineGuidance(modifier: Modifier) {
    ImageParkGuidance(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(0.7f)
        ,
    )

    ProfileImage(
        modifier = modifier
            .fillMaxHeight(0.09f)
            .fillMaxWidth(0.7f)
    )
}