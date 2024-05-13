package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun SliderOnboarding(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider = listOf(
        painterResource(id = R.drawable.onboarding_1),
        painterResource(id = R.drawable.onboarding_2),
        painterResource(id = R.drawable.onboarding_3)
    )

        HorizontalPager(
            count = imageSlider.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
                .fillMaxWidth(),
        ) {
            pageIndex ->

                Image(
                    painter = imageSlider[pageIndex],
                    contentDescription = "",
                    modifier = modifier
                        .fillMaxWidth(),

                )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = modifier
                .padding(16.dp),
            activeColor = BluePark,
            inactiveColor = GreyDark,
            spacing = 20.dp
        )
}

@Preview(showBackground = true)
@Composable
fun SliderPreview() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SliderOnboarding(modifier = Modifier)
    }
}

