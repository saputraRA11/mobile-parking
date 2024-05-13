package com.example.parking.ui.component

import android.service.autofill.OnClickAction
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.parking.ui.theme.BluePark
import com.example.parking.R

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    IconVector:ImageVector,
    description:String = "",
    isOutlined:Boolean = true,
    borderSize:Dp = 1.dp,
    effect:() -> Unit = {}
) {
    if(isOutlined) {
        Box(
            modifier = modifier
                .clickable {
                    effect()
                }
                .border(borderSize, color = color, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                IconVector,
                contentDescription = description,
                tint = color,
                modifier = modifier
            )
        }
    } else {
        Icon(
            IconVector,
            contentDescription = description,
            tint = color,
            modifier = modifier
                .clickable {
                    effect()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackIconPreview() {
    Column (
        modifier = Modifier
            .padding(5.dp)
    ){
        CustomIcon(
            color = BluePark,
            IconVector = Icons.Default.KeyboardArrowLeft,
            isOutlined = false,
            modifier = Modifier.size(10.dp)
        )

        CustomIcon(
            color = BluePark,
            IconVector = Icons.Default.Clear,
            isOutlined = true ,
            modifier = Modifier.size(10.dp)
        )

        CustomIcon(
            color = BluePark,
            IconVector = ImageVector.vectorResource(id = R.drawable.help_support_foreground),
            isOutlined = false ,
            modifier = Modifier.size(30.dp)
        )
    }
}