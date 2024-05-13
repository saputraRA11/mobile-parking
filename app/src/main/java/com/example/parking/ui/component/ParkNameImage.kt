package com.example.parking.ui.component

import android.service.autofill.CustomDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R

@Composable
fun ImageParkName(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.logo_name),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    id:Int = R.drawable.logo_name,
    description: String = ""
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = description,
        modifier = modifier
    )
}

@Composable
fun ImageParkGuidance() {
    Image(
        painter = painterResource(id = R.drawable.logo_penjaga),
        contentDescription = null,
    )
}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    id:Int = R.drawable.example_foto,
    size:Int = 100
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size((size).dp)
            .clip(CircleShape)
        ,
    )
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

    }

    ProfileImage(
        modifier = Modifier.padding(20.dp),
        size = 50
    )
}