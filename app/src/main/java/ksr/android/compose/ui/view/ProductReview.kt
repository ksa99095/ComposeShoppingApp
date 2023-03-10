package ksr.android.compose.ui.view

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ksr.android.compose.R
import ksr.android.compose.data.ReviewData
import ksr.android.compose.ui.theme.Gray_949494
import ksr.android.compose.ui.theme.Gray_F2F2F2
import ksr.android.compose.ui.theme.Primary
import ksr.android.compose.ui.theme.White
import kotlin.math.roundToInt

@Composable
fun ProductReview(
    reviewData: MutableState<List<ReviewData>>,
    checkedFilter: MutableState<String>,
    modifier: Modifier = Modifier,
    filterClick: () -> Unit = {}
) {
    var imageExpand by remember { mutableStateOf(false) }
    var showReviewCount by remember { mutableStateOf(3) }


    var rating = 0f
    reviewData.value.forEach {
        rating += it.rating.toFloat()
    }

    rating /= reviewData.value.size
    rating = (rating * 10).roundToInt() / 10.0f


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {

            Row {
                ReviewStar(rating, 28, 140)

                Text(
                    text = rating.toString().replace(".0", ""),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(CenterVertically),
                    color = Primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 18.sp
                )

                Text(
                    text = " / 5",
                    modifier = Modifier.align(CenterVertically),
                    color = Gray_949494,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 18.sp
                )

            }

            Row(
                modifier = Modifier.clickable { filterClick() },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = CenterVertically
            ) {

                Text(
                    text = checkedFilter.value,
                    modifier = Modifier.align(CenterVertically).padding(end = 4.dp),
                    color = Gray_949494,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 16.sp
                )

                Image(
                    painter = painterResource(id = R.drawable.swap_vert),
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterVertically)
                        .size(18.dp),
                    colorFilter = ColorFilter.tint(Gray_949494)
                )
            }
        }

        Divider(
            modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
            color = Gray_F2F2F2,
            thickness = 1.dp
        )



        for (i in 0 until showReviewCount) {
            ReviewItem(data = reviewData.value[i])
            if (reviewData.value[i] != reviewData.value[showReviewCount - 1]) {
                Divider(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    color = Gray_F2F2F2,
                    thickness = 1.dp
                )
            }
        }


        ShowMoreButton(
            text = stringResource(id = if (imageExpand) R.string.collapse_review else R.string.expand_review),
            icon = if (imageExpand) R.drawable.arrow_up else R.drawable.arrow_down,
            onClick = {
                showReviewCount = if (imageExpand) 3 else reviewData.value.size
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