package ksr.android.compose.ui.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ksr.android.compose.R
import ksr.android.compose.ui.theme.Gray_F2F2F2
import ksr.android.compose.ui.theme.White

@Composable
fun ProductDetail(modifier: Modifier = Modifier) {

    var imageExpand by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .animateContentSize(animationSpec = tween(1000))
    ) {
        Image(
            painter = painterResource(id = R.drawable.product_detail),
            contentDescription = null,
            modifier = if (imageExpand) {
                Modifier.fillMaxWidth()
            } else {
                Modifier.fillMaxWidth().height(540.dp)
            },
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )

        ShowMoreButton(
            text = stringResource(id = if (imageExpand) R.string.collapse_product_detail else R.string.expand_product_detail),
            icon = if (imageExpand) R.drawable.arrow_up else R.drawable.arrow_down,
            onClick = {
                imageExpand = !imageExpand
            }
        )

        Divider(
            modifier = Modifier.padding(top = 19.dp),
            color = Gray_F2F2F2,
            thickness = 1.dp
        )
    }
}