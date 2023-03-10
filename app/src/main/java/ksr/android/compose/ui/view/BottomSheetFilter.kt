package ksr.android.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ksr.android.compose.R
import ksr.android.compose.ui.theme.Gray_DFDFDF
import ksr.android.compose.ui.theme.Primary
import ksr.android.compose.ui.theme.Secondary


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetFilter(
    filterList: List<String>,
    modalBottomSheetState: ModalBottomSheetState,
    checkedFilter: MutableState<String>,
    coroutineScope: CoroutineScope,
    filterClickListener: (String) -> Unit = {},
    content: @Composable () -> Unit
) {

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetElevation = 0.dp,
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(start = 24.dp, top = 12.dp, end = 12.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.filter),
                        modifier = Modifier.padding(top = 12.dp, bottom = 25.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 20.sp,
                        color = Secondary
                    )

                    Image(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(24.dp)
                            .clickable {
                                coroutineScope.launch { modalBottomSheetState.hide() }
                            }
                    )
                }


                filterList.forEach {
                    Filter(
                        it,
                        checkedFilter.value == it,
                        Modifier.padding(top = 4.dp)
                            .clickable {
                                checkedFilter.value = it

                                filterClickListener(checkedFilter.value)

                                coroutineScope.launch {
                                    modalBottomSheetState.hide()
                                }
                            }
                    )
                }
            }
        }
    ) {
        content()
    }
}

@Composable
fun Filter(name: String, check: Boolean, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(bottom = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.radio_button_checked),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically),
            colorFilter = ColorFilter.tint(if (check) Primary else Gray_DFDFDF)
        )

        Text(text = name, fontSize = 16.sp, fontWeight = FontWeight(400), lineHeight = 24.sp, modifier = Modifier
            .padding(start = 14.dp)
            .align(Alignment.CenterVertically))
    }
}