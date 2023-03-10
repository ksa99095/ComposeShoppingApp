package ksr.android.compose.ui

import android.os.Bundle
import android.os.Process

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ksr.android.compose.R
import ksr.android.compose.common.ToastUtil
import ksr.android.compose.model.ReviewData
import ksr.android.compose.ui.theme.Compose_kotlinTheme
import ksr.android.compose.ui.theme.White
import ksr.android.compose.ui.view.*

class MainActivity : ComponentActivity() {

    var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() > backKeyPressedTime + 3000) {
                    backKeyPressedTime = System.currentTimeMillis()
                    ToastUtil.showShort(R.string.toast_back_press_msg)

                    return
                }
                if (System.currentTimeMillis() <= backKeyPressedTime + 3000) {
                    moveTaskToBack(true)
                    finishAndRemoveTask()
                    Process.killProcess(Process.myPid())
                }

            }
        })
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun MyApp() {

        val productImageList: List<Int> = listOf(R.drawable.product_1, R.drawable.product_2, R.drawable.product_3)
        val reviewDataList: List<ReviewData> = listOf(
            ReviewData("4.5", "12ae***", "2022.11.15", listOf(R.drawable.review1_1, R.drawable.review1_2), "좋아요"),
            ReviewData("4.0", "ksr***", "2022.11.09", listOf(R.drawable.review2_1, R.drawable.review2_2, R.drawable.review2_3), "맛있어요!"),
            ReviewData("4.5", "barca***", "2022.11.03", listOf(R.drawable.review3_1), "Gracias!"),
            ReviewData("4.5", "tot***", "2022.10.26", listOf(R.drawable.review4_1, R.drawable.review4_2, R.drawable.review4_3, R.drawable.review4_4 ,R.drawable.review4_5), "Good!"),
            ReviewData("5.0", "roma***", "2022.10.23", listOf(R.drawable.review5_1, R.drawable.review5_2, R.drawable.review5_3, R.drawable.review5_4, R.drawable.review5_5 ,R.drawable.review5_6 ,R.drawable.review5_7, R.drawable.review5_8), "Delizioso!"),
            ReviewData("5.0", "bayern***", "2022.10.17", listOf(R.drawable.review6_1, R.drawable.review6_2, R.drawable.review6_3, R.drawable.review6_4), "Beste")
        )

        val coroutineScope = rememberCoroutineScope()
        val scrollState = rememberScrollState()

        var cartCount by remember { mutableStateOf(0) }
        var dialogShow by remember { mutableStateOf(false) }
        var popupShow by remember { mutableStateOf(false) }
        val review = remember { mutableStateOf(reviewDataList) }


        var detailPosition = 0
        var reviewPosition = 0

        var appBarHeight = 0   // 44.dp to px
        var tabBarHeight = 0    // 53.dp to px

        var offset by remember { mutableStateOf(0) }

        val horizontalViewPagerHeight = LocalConfiguration.current.screenWidthDp

        val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

        val filterList = listOf("최신순", "평점낮은순", "평점높은순")
        val checkedFilter = remember { mutableStateOf(filterList.first()) }

        fun showCartPopUp() {
            if (!popupShow) {
                popupShow = true
                cartCount++
                coroutineScope.launch {
                    delay(1500L)
                    popupShow = false
                }
            }
        }

        fun scrollAlpha(scrollY: Int, maxDist: Int): Float {
            val minDist = 0

            return when {
                scrollY <= minDist -> 1f
                scrollY > maxDist -> 0f
                else -> 1f - (1f / maxDist) * scrollY
            }
        }

        fun activeTab(detailPosition: Int, reviewPosition: Int, scrollY: Int, mode: Int): Boolean {
            return when (mode) {    // 1: detail tab, 2: review tab
                1 -> { scrollY in detailPosition until reviewPosition }
                2 -> { reviewPosition <= scrollY }
                else -> { false }
            }
        }





        Compose_kotlinTheme {

            BottomSheetFilter(
                filterList,
                modalBottomSheetState,
                checkedFilter,
                coroutineScope,
                filterClickListener = { filter ->
                    when (filter) {
                        "최신순" -> { review.value = review.value.sortedByDescending { it.date }}
                        "평점낮은순" -> { review.value = review.value.sortedBy { it.rating } }
                        "평점높은순" -> { review.value = review.value.sortedByDescending { it.rating } }
                    }
                }
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White)
                ) {

                    /** content */
                    Column(
                        modifier = Modifier.verticalScroll(state = scrollState)
                    ) {

                        Spacer(modifier = Modifier.height(44.dp))

                        // Product Image
                        HorizontalViewPager(
                            productImageList,
                            modifier = Modifier
                                .graphicsLayer {
                                    translationY = scrollState.value.toFloat()
                                    alpha = scrollAlpha(scrollState.value, horizontalViewPagerHeight.dp.toPx().toInt())
                                }
                        )

                        // Product Info
                        ProductInfo(
                            onCheckCartCompanyClick = { ToastUtil.showShort(R.string.toast_card_click_msg) },
                            onCheckCouponClick = { ToastUtil.showShort(R.string.toast_coupon_click_msg) }
                        )

                        Spacer(modifier = Modifier.height(53.dp))

                        // Product Detail Image
                        ProductDetail(
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    detailPosition = coordinates.positionInParent().y.toInt()
                                }
                        )

                        // Product Review
                        ProductReview(
                            review,
                            checkedFilter,
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    reviewPosition = coordinates.positionInParent().y.toInt()
                                },
                            filterClick = { coroutineScope.launch { modalBottomSheetState.show() } }
                        )

                        // Other Product Info
                        OtherProductInfo(
                            onReqProductInfoClick = { ToastUtil.showShort(R.string.toast_product_info_click_msg) },
                            onEtcInfoClick = { ToastUtil.showShort(R.string.toast_etc_click_msg) }
                        )

                        Spacer(modifier = Modifier.height(150.dp))
                    }

                    /** AppBar */
                    AppBar(
                        stringResource(id = R.string.title_product_info),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .onGloballyPositioned { coordinates ->
                                appBarHeight = coordinates.size.height
                            },
                        cartCount = cartCount,
                        backVisible = true,
                        searchVisible = true,
                        cartVisible = true,
                        closeVisible = false,
                        onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                        onSearchClick = { ToastUtil.showShort(R.string.toast_search_click_msg) },
                        onCartClick = { ToastUtil.showShort(R.string.toast_cart_click_msg) },
                        closeEvent = {}
                    )

                    /** TabBar */
                    TabBar(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                tabBarHeight = coordinates.size.height
                            }
                            .offset {
                                offset = ((detailPosition - tabBarHeight) - scrollState.value).coerceAtLeast(appBarHeight)
                                if (offset == 0) IntOffset(x = 0, y = -1000) else IntOffset(x = 0, y = offset)
                            },
                        detailTabClick = {
                            coroutineScope.launch {
                                scrollState.animateScrollTo(detailPosition - tabBarHeight - appBarHeight)    // -(tabBar Height + appBarHeight)
                            }
                        },
                        reviewTabClick = {
                            coroutineScope.launch {
                                scrollState.animateScrollTo(reviewPosition - tabBarHeight - appBarHeight)    // -(tabBar Height + appBarHeight)
                            }
                        },
                        detailTabActive = activeTab(
                            detailPosition - tabBarHeight - appBarHeight,
                            reviewPosition - tabBarHeight - appBarHeight,
                            scrollState.value,
                            1
                        ),
                        reviewTabActive = activeTab(
                            detailPosition - tabBarHeight - appBarHeight,
                            reviewPosition - tabBarHeight - appBarHeight,
                            scrollState.value,
                            2
                        )
                    )

                    /** top Button */
                    TopButton(
                        onClick = {
                            coroutineScope.launch {
                                scrollState.animateScrollTo(0)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 89.dp, end = 17.dp),
                        isVisible = scrollState.value != 0
                    )

                    /** BottomBar */
                    BottomTabBar(
                        modifier = Modifier
                            .align(Alignment.BottomCenter),
                        buyClick = { dialogShow = true },
                        cartClick = { showCartPopUp() }
                    )

                    /** Dialog */
                    if (dialogShow) {
                        CustomDialog(
                            title = stringResource(id = R.string.dialog_title),
                            buttonClick = { dialogShow = false }
                        )
                    }

                    /** PopUp */
                    AddCartPopUp(popupShow)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApp()
    }
}