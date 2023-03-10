package ksr.android.compose.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ksr.android.compose.R
import ksr.android.compose.common.NoRippleInteractionSource
import ksr.android.compose.ui.theme.Gray_F2F2F2
import ksr.android.compose.ui.theme.Gray_777777
import ksr.android.compose.ui.theme.Secondary
import ksr.android.compose.ui.theme.White

@Composable
fun TabBar(
    modifier: Modifier = Modifier,
    detailTabClick: () -> Unit,
    reviewTabClick: () -> Unit,
    detailTabActive: Boolean,
    reviewTabActive: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(53.dp)
            .background(White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .clickable(
                    onClick = { detailTabClick() },
                    interactionSource = NoRippleInteractionSource.interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.tab_product_detail),
                color = if (detailTabActive) Secondary else Gray_777777,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                lineHeight = 18.sp
            )

            Divider(
                modifier = Modifier
                    .height(
                        if (detailTabActive) 2.dp else 1.dp
                    )
                    .fillMaxWidth()
                    .background(
                        if (detailTabActive) Secondary else Gray_F2F2F2
                    )
                    .align(Alignment.BottomCenter)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable(
                    onClick = { reviewTabClick() },
                    interactionSource = NoRippleInteractionSource.interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.tab_review),
                color = if (reviewTabActive) Secondary else Gray_777777,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                lineHeight = 18.sp
            )

            Divider(
                modifier = Modifier
                    .height(
                        if (reviewTabActive) 2.dp else 1.dp
                    )
                    .fillMaxWidth()
                    .background(
                        if (reviewTabActive) Secondary else Gray_F2F2F2
                    )
                    .align(Alignment.BottomCenter)
            )
        }
    }
}