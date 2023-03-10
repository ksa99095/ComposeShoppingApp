package ksr.android.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ksr.android.compose.R
import ksr.android.compose.common.NoRippleInteractionSource
import ksr.android.compose.ui.theme.Gray_F2F2F2
import ksr.android.compose.ui.theme.Secondary

@Composable
fun OtherProductInfo(
    onReqProductInfoClick: () -> Unit,
    onEtcInfoClick: () -> Unit
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 17.dp, end = 20.dp, bottom = 18.dp)
                .clickable(
                    onClick = { onReqProductInfoClick() },
                    interactionSource = NoRippleInteractionSource.interactionSource,
                    indication = null
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.req_product_info),
                color = Secondary,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 20.sp
            )
            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        Divider(color = Gray_F2F2F2, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 17.dp, end = 20.dp, bottom = 18.dp)
                .clickable(
                    onClick = { onEtcInfoClick() },
                    interactionSource = NoRippleInteractionSource.interactionSource,
                    indication = null
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.etc_info),
                color = Secondary,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 20.sp
            )
            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        Divider(color = Gray_F2F2F2, thickness = 1.dp)
    }
}