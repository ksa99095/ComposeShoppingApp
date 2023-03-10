package ksr.android.compose.ui.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ksr.android.compose.model.ReviewData
import ksr.android.compose.ui.ReviewImageActivity
import ksr.android.compose.ui.theme.*

@Composable
fun ReviewItem(data: ReviewData) {

    val mContext = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var imageSize = screenWidth - 60.dp     // padding(start 20dp, end 20dp), divider(5dp * 4)
    imageSize /= 5                          // image size

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 12.dp)
    ) {
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ReviewStar(rating = data.rating.toFloat(), height = 20, width = 100)
                Text(
                    text = data.id,
                    modifier = Modifier.padding(start = 12.dp),
                    color = Secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 16.sp
                )
            }

            Text(
                text = data.date,
                color = Gray_777777,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp
            )
        }


        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            itemsIndexed(
                items = data.imageList,
                key = null
            ) { index, image ->

                if (index < 5) {

                    if (data.imageList.size > 5 && index == 4) {
                        Box(
                            modifier = Modifier
                                .size(imageSize, imageSize)
                                .clip(RoundedCornerShape(5.dp))
                                .background(Gray_B7B7B7)
                        ) {
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(imageSize, imageSize)
                                    .clip(RoundedCornerShape(5.dp))
                                    .alpha(0.2f)
                                    .clickable {
                                        mContext.startActivity(Intent(mContext, ReviewImageActivity::class.java)
                                            .putExtra("image", data.imageList.toIntArray())
                                        )
                                    },
                                contentScale = ContentScale.FillBounds
                            )
                            
                            Text(
                                text = "+${data.imageList.size - 5}",
                                modifier = Modifier.align(Alignment.Center),
                                color = Gray_777777,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 16.sp
                            )
                        }
                    } else {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(imageSize, imageSize)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    mContext.startActivity(Intent(mContext, ReviewImageActivity::class.java)
                                        .putExtra("image", data.imageList.toIntArray())
                                    )
                                },
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }

                if (index < 4) {
                    Divider(
                        modifier = Modifier
                            .width(5.dp)
                            .height(imageSize),
                        color = White
                    )
                }
            }
        }

        Text(
            text = data.reviewContent,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            lineHeight = 16.sp,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}