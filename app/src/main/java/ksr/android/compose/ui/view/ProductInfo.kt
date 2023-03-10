package ksr.android.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import ksr.android.compose.R
import ksr.android.compose.common.NoRippleInteractionSource
import ksr.android.compose.ui.theme.*

@Composable
fun ProductInfo(
    onCheckCartCompanyClick: () -> Unit,
    onCheckCouponClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(White)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.product_name),
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
            color = Secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            lineHeight = TextUnit.Unspecified
        )

        Text(
            text = stringResource(id = R.string.unit_price),
            textDecoration = LineThrough,
            modifier = Modifier.padding(start = 20.dp, top = 8.dp, end = 20.dp),
            color = Gray_B7B7B7,
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            lineHeight = 18.sp
        )

        Row(
            modifier = Modifier.padding(start = 20.dp, top = 9.dp, end = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.discount_rate),
                color = Primary,
                fontSize = 26.sp,
                fontWeight = FontWeight(700),
                lineHeight = 28.sp
            )
            Text(
                text = stringResource(id = R.string.amount),
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                lineHeight = 28.sp
            )
            Text(
                text = stringResource(id = R.string.base_price),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .align(Alignment.CenterVertically),
                color = Gray_B7B7B7,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp
            )
        }

        Divider(
            color = Gray_F2F2F2, thickness = 1.dp, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Image(painter = painterResource(id = R.drawable.car), contentDescription = null, modifier = Modifier.size(16.dp))

            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = stringResource(id = R.string.shipping_type),
                    color = Gray_777777,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 20.sp
                )

                Text(
                    text = stringResource(id = R.string.due_date),
                    modifier = Modifier.padding(top = 8.dp),
                    color = Secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 18.sp
                )

                Text(
                    text = stringResource(id = R.string.shipping_cost),
                    modifier = Modifier.padding(top = 8.dp),
                    color = Gray_777777,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 20.sp
                )
            }
        }

        Divider(
            color = Gray_F2F2F2, thickness = 1.dp, modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
        )

        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Image(painter = painterResource(id = R.drawable.card), contentDescription = null, modifier = Modifier.size(16.dp))

            Column(modifier = Modifier.padding(start = 12.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.card_benefit),
                        modifier = Modifier.weight(0.3f),
                        color = Secondary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 18.sp
                    )

                    Text(
                        text = stringResource(id = R.string.card_benefit_price),
                        modifier = Modifier
                            .weight(0.45f)
                            .padding(start = 8.dp),
                        color = Gray_777777,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp
                    )

                    ConstraintLayout(
                        modifier = Modifier
                            .weight(0.3f)
                            .clickable(
                                onClick = { onCheckCartCompanyClick() },
                                interactionSource = NoRippleInteractionSource.interactionSource,
                                indication = null
                            )
                    ) {
                        val (text, image) = createRefs()

                        Text(
                            text = stringResource(id = R.string.check_card_company),
                            color = Gray_777777,
                            fontSize = 15.sp,
                            fontWeight = FontWeight(400),
                            lineHeight = 16.sp,
                            modifier = Modifier.constrainAs(text) {
                                top.linkTo(parent.top)
                                end.linkTo(image.start, margin = 8.dp)
                                bottom.linkTo(parent.bottom)
                            }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.arrow_right),
                            contentDescription = null,
                            modifier = Modifier
                                .size(14.dp)
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.coupon_sale),
                        modifier = Modifier.weight(0.3f),
                        color = Secondary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 18.sp
                    )

                    Text(
                        text = stringResource(id = R.string.coupon_sale_price),
                        modifier = Modifier
                            .weight(0.45f)
                            .padding(start = 8.dp),
                        color = Gray_777777,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp
                    )


                    ConstraintLayout(
                        modifier = Modifier
                            .weight(0.3f)
                            .clickable(
                                onClick = { onCheckCouponClick() },
                                interactionSource = NoRippleInteractionSource.interactionSource,
                                indication = null
                            )
                    ) {
                        val (text, image) = createRefs()

                        Text(
                            text = stringResource(id = R.string.check_coupon),
                            color = Gray_777777,
                            fontSize = 15.sp,
                            fontWeight = FontWeight(400),
                            lineHeight = 16.sp,
                            modifier = Modifier.constrainAs(text) {
                                top.linkTo(parent.top)
                                end.linkTo(image.start, margin = 8.dp)
                                bottom.linkTo(parent.bottom)
                            }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.arrow_right),
                            contentDescription = null,
                            modifier = Modifier
                                .size(14.dp)
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }
            }
        }

        Divider(
            color = Gray_F2F2F2, thickness = 1.dp, modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
        )

        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Image(painter = painterResource(id = R.drawable.campaign), contentDescription = null, modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.event_name),
                    color = Secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 18.sp
                )

                Text(
                    text = stringResource(id = R.string.event_period),
                    modifier = Modifier.padding(top = 8.dp),
                    color = Gray_777777,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 16.sp
                )
            }
        }

        Divider(
            color = Gray_F2F2F2, thickness = 1.dp, modifier = Modifier.padding(top = 16.dp)
        )
    }
}