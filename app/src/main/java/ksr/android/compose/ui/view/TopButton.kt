package ksr.android.compose.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ksr.android.compose.R
import ksr.android.compose.common.NoRippleInteractionSource

@Composable
fun TopButton(modifier: Modifier = Modifier, onClick: () -> Unit, isVisible: Boolean) {

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(initialAlpha = 0.1f),
        exit = fadeOut(animationSpec = tween(durationMillis = 250)),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.top_button),
            contentDescription = null,
            modifier = Modifier.size(42.dp).clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
            ) {
                onClick()
            }
        )
    }
}