package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.GreyDark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderStatistics(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider = listOf(
        painterResource(id = R.drawable.statistics),
        painterResource(id = R.drawable.statistics),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = imageSlider.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
        ) { pageIndex ->
            Image(
                painter = imageSlider[pageIndex],
                contentDescription = "Image On Boarding",
                modifier = Modifier
                    .size(300.dp)
                    .aspectRatio(1f)
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(top = 6.dp),
            activeColor = BluePark,
            inactiveColor = GreyDark,
            spacing = 8.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SliderStatisticsPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SliderStatistics(modifier = Modifier.fillMaxWidth())
    }
}
