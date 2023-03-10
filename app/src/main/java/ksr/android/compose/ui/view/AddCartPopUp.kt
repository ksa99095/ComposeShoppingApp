package ksr.android.compose.ui.view

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ksr.android.compose.R
import ksr.android.compose.ui.theme.Primary
import ksr.android.compose.ui.theme.White

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddCartPopUp(isVisible: Boolean) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(121.dp)
                    .clip(RoundedCornerShape(61.dp))
                    .background(Primary.copy(0.7f))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_cart),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(55.dp),
                    colorFilter = ColorFilter.tint(White)
                )
            }
        }
    }
}