package ksr.android.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ksr.android.compose.R
import ksr.android.compose.common.NoRippleInteractionSource
import ksr.android.compose.ui.theme.Black
import ksr.android.compose.ui.theme.Gray_F2F2F2
import ksr.android.compose.ui.theme.Primary
import ksr.android.compose.ui.theme.White

@Composable
fun AppBar(
    title: String,
    modifier: Modifier = Modifier,
    cartCount: Int = 0,
    backVisible: Boolean = true,
    searchVisible: Boolean = true,
    cartVisible: Boolean = true,
    closeVisible: Boolean = false,
    onBackPressed: () -> Unit,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    closeEvent: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(White),
    ) {
        if (backVisible) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
                    .size(32.dp)
                    .clickable(
                        onClick = { onBackPressed() },
                        interactionSource = NoRippleInteractionSource.interactionSource,
                        indication = null
                    ),
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = title,
            color = Black,
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (searchVisible) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 4.dp)
                        .clickable(
                            onClick = { onSearchClick() },
                            interactionSource = NoRippleInteractionSource.interactionSource,
                            indication = null
                        ),
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null
                )
            }

            if (cartVisible) {

                Box {
                    Image(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 4.dp, end = 4.dp)
                            .clickable(
                                onClick = { onCartClick() },
                                interactionSource = NoRippleInteractionSource.interactionSource,
                                indication = null
                            )
                    )

                    if (cartCount > 0) {
                        Text(
                            text = if (cartCount >= 99) "99" else "$cartCount",
                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.TopEnd)
                                .clip(RoundedCornerShape(16.dp))
                                .background(color = Primary)
                                .wrapContentHeight(),
                            color = White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight(700),
                            textAlign = TextAlign.Center,
                            lineHeight = 10.sp,
                        )
                    }
                }
            }

            if (closeVisible) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 4.dp)
                        .clickable(
                            onClick = { closeEvent() },
                            interactionSource = NoRippleInteractionSource.interactionSource,
                            indication = null
                        ),
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = null
                )
            }
        }

        Divider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Gray_F2F2F2,
            thickness = 1.dp
        )
    }
}