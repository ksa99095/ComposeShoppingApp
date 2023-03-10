package ksr.android.compose.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ksr.android.compose.R
import ksr.android.compose.ui.theme.White

@Composable
fun CustomDialog(title: String, buttonClick: () -> Unit) {

    Dialog(
        onDismissRequest = { },
        content = {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = White
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 10.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 16.sp
                    )
                    Button(
                        onClick = { buttonClick() },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp)
                    ) {
                        Text(text = stringResource(id = R.string.ok_btn))
                    }
                }
            }
        }
    )
}