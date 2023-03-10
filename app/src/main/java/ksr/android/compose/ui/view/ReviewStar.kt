package ksr.android.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ksr.android.compose.R
import ksr.android.compose.ui.theme.Gray_F2F2F2
import ksr.android.compose.ui.theme.Primary

@Composable
fun ReviewStar(rating: Float, height: Int, width: Int) {

    val drawRating = rating * (width / 5)

    Box(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Gray_F2F2F2)
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .background(Primary)
                .fillMaxHeight()
                .width(drawRating.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.icon_rating_large),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}