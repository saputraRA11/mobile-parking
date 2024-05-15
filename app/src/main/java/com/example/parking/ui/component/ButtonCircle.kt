package com.example.parking.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.ui.theme.BluePark

@Composable
fun ButtonCircle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isOutlined:Boolean = false,
    text:String = "",
    backgroundColor:ButtonColors = ButtonDefaults.buttonColors(),
    border:BorderStroke = BorderStroke(1.dp,Color.Black),
    textAlign:Arrangement.Horizontal = Arrangement.Center,
    trailingIcon: @Composable () -> Unit = {},
    style: TextStyle = TextStyle.Default,
    fontWeight: FontWeight = FontWeight.Normal,
    enable:Boolean = true

) {
    if(!isOutlined) {
        Button(
            modifier = modifier,
            onClick = onClick,
            colors = backgroundColor,
            enabled = enable
        ) {
            Row(
                horizontalArrangement = textAlign,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = text,
                    style = style,
                    fontWeight = fontWeight
                )

                trailingIcon()
            }
        }
    } else {
        OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            colors = backgroundColor,
            border = border
        ) {
            Row(
                horizontalArrangement = textAlign,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = text,
                    style = style,
                    fontWeight = fontWeight
                )

                trailingIcon()
            }
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun ButtonPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ButtonCircle(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Masuk",
            backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
        )

        ButtonCircle(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            text = "Pengguna Baru? Buat akun",
            backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
            isOutlined = true,
            border = BorderStroke(1.dp, BluePark),
        )
    }


}

