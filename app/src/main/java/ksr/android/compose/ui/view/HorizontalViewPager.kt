package ksr.android.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import ksr.android.compose.ui.theme.Black
import ksr.android.compose.ui.theme.Primary

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalViewPager(productImageList: List<Int>, modifier: Modifier = Modifier) {
    val imageList = ArrayList<Painter>()

    productImageList.forEach {
        imageList.add(painterResource(id = it))
    }

    val pagerState = rememberPagerState(initialPage = 0)
    val tabIndex = pagerState.currentPage

    Box(modifier = Modifier.background(Black)) {
        HorizontalPager(
            modifier = modifier.aspectRatio(1f),
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

        // TAB
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = modifier
                .height(2.dp)
                .align(Alignment.BottomCenter),
            backgroundColor = Color.Transparent,
            contentColor = Primary,
            divider = {},   // divider null
            indicator = { tabPositions ->       // paging tab
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            imageList.forEachIndexed { index, _ ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {}    // onClick null
                )
            }
        }
    }

}