package com.example.parking.ui.component

import android.service.autofill.CustomDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R
import com.example.parking.ui.utils.QrCodeGenerator

@Composable
fun ImageParkName(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.logo_name),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    id:Int = R.drawable.logo_name,
    description: String = "",
    painter: Painter = QrCodeGenerator(content = "kosong")
) {
    Image(
        painter = if(id != R.drawable.logo_name) painterResource(id = id) else painter,
        contentDescription = description,
        modifier = modifier,
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ImageParkGuidance(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.logo_penjaga),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    id:Int = R.drawable.example_foto,
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clip(CircleShape)

        ,
    )
}

@Composable
fun ManagementName(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.management),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun ProfileManagementImage(
    modifier: Modifier = Modifier,
    id:Int = R.drawable.managementimage,
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
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
    )
}