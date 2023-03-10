package ksr.android.compose.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ksr.android.compose.R
import ksr.android.compose.common.NoRippleInteractionSource
import ksr.android.compose.ui.theme.Gray_B7B7B7
import ksr.android.compose.ui.theme.White

@Composable
fun ShowMoreButton(text: String, icon: Int, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(start = 20.dp, end = 20.dp),
        border = BorderStroke(1.dp, Gray_B7B7B7),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = White),
        interactionSource = NoRippleInteractionSource,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 20.sp,
                modifier = Modifier.padding(end = 4.dp)
            )
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Preview
@Composable
fun ShowMoreButtonPreview() {
    ShowMoreButton(text = "접기", icon = R.drawable.arrow_up, onClick = {})
}