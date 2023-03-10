package ksr.android.compose.ui.view

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ksr.android.compose.R
import ksr.android.compose.common.NoRippleInteractionSource
import ksr.android.compose.ui.theme.Gray_DFDFDF
import ksr.android.compose.ui.theme.Red_EB604D
import ksr.android.compose.ui.theme.Secondary
import ksr.android.compose.ui.theme.White

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomTabBar(modifier: Modifier = Modifier, buyClick: () -> Unit, cartClick: () -> Unit) {

    var favoriteState by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .paint(
                painter = painterResource(id = R.drawable.bottom_tabbar),
                contentScale = ContentScale.FillBounds
            )
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
        ) {
            OutlinedButton(
                onClick = { favoriteState = !favoriteState },
                border = BorderStroke(1.dp, Gray_DFDFDF),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(48.dp),
                contentPadding = PaddingValues(0.dp),
                interactionSource = NoRippleInteractionSource
            ) {

                Image(
                    painter = painterResource(id = R.drawable.favorite_off),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }

            this@Row.AnimatedVisibility(
                visible = favoriteState,
                enter = scaleIn(),
                exit = scaleOut(),
                modifier = Modifier.padding(start = 9.dp, top = 9.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.favorite_on),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Red_EB604D)
                )
            }
        }

        OutlinedButton(
            onClick = { buyClick() },
            border = BorderStroke(1.dp, Gray_DFDFDF),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(142.dp)
                .height(48.dp)
                .padding(start = 5.dp),
            interactionSource = NoRippleInteractionSource
        ) {
            Text(
                text = stringResource(id = R.string.bottom_buy_now),
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Secondary,
                fontSize = 17.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp
            )
        }

        OutlinedButton(
            onClick = { cartClick() },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(148.dp)
                .height(48.dp)
                .padding(start = 5.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Secondary),
            interactionSource = NoRippleInteractionSource
        ) {
            Text(
                text = stringResource(id = R.string.bottom_add_cart),
                modifier = Modifier.align(Alignment.CenterVertically),
                color = White,
                fontSize = 17.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp
            )
        }
    }
}