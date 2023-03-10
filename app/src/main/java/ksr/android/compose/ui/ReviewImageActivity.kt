package ksr.android.compose.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ksr.android.compose.R
import ksr.android.compose.ui.theme.Black
import ksr.android.compose.ui.theme.White
import ksr.android.compose.ui.view.AppBar

class ReviewImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageList = intent.getIntArrayExtra("image")

        setContent {
            if (imageList != null) {
                ReviewImage(imageList.toList())
            } else {
                finish()
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ReviewImage(productImageList: List<Int>) {

        val activity = LocalContext.current as Activity
        val imageList = ArrayList<Painter>()

        productImageList.forEach {
            imageList.add(painterResource(id = it))
        }

        val pagerState = rememberPagerState(initialPage = 0)



        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AppBar(
                title = stringResource(id = R.string.title_product_info),
                backVisible = false,
                searchVisible = false,
                cartVisible = false,
                closeVisible = true,
                onBackPressed = {},
                onSearchClick = {},
                onCartClick = {},
                closeEvent = {
                    activity.finish()
                }
            )

            Box {
                HorizontalPager(
                    modifier = Modifier.aspectRatio(1f),
                    count = imageList.size,
                    state = pagerState
                ) { page ->
                    Image(
                        painter = imageList[page],
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                        .width(40.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Black.copy(alpha = 0.2f)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${pagerState.currentPage + 1}",
                        color = White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 16.sp
                    )
                    Text(
                        text = " / ${imageList.size}",
                        color = White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}