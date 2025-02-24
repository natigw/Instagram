package natig.mammadov.ui_toolkit.components.buttons.square

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import natig.mammadov.ui_toolkit.components.buttons.ButtonState
import natig.mammadov.ui_toolkit.theme.ButtonDefault
import natig.mammadov.ui_toolkit.theme.ButtonDisabled
import natig.mammadov.ui_toolkit.theme.ButtonLoading
import natig.mammadov.ui_toolkit.theme.IconButtonPadding
import natig.mammadov.ui_toolkit.theme.IconDefault
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.theme.IconSemiSubtle
import natig.mammadov.ui_toolkit.theme.SquareButtonShape
import natig.mammadov.ui_toolkit.R.drawable as drawableR
import natig.mammadov.ui_toolkit.R.string as stringR

@Composable
fun DefaultIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    @DrawableRes onCompletedIconRes: Int? = null,
    state: ButtonState,
    onClick: (ButtonState) -> Unit
) {

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> ButtonDisabled
            ButtonState.ENABLED -> ButtonDefault
            ButtonState.LOADING -> ButtonLoading
            ButtonState.COMPLETED -> ButtonDefault
        },
        label = "Default Icon button background color animation"
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> IconSemiSubtle
            ButtonState.ENABLED -> IconDefault
            ButtonState.LOADING -> IconDefaultInverted
            ButtonState.COMPLETED -> IconDefault
        },
        label = "Default Icon button content color animation"
    )

    Box(
        modifier = modifier
            .clip(SquareButtonShape)
            .background(backgroundColor)
            .clickable(
                enabled = state != ButtonState.DISABLED && state != ButtonState.LOADING,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {
                    onClick(state)
                }
            )
            .padding(IconButtonPadding),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ButtonState.DISABLED, ButtonState.ENABLED -> {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(iconRes),
                    contentDescription = stringResource(stringR.action_icon_button),
                    tint = contentColor
                )
            }

            ButtonState.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.requiredSize(16.dp),
                    strokeWidth = 2.dp,
                    strokeCap = StrokeCap.Round,
                    color = contentColor
                )
            }

            ButtonState.COMPLETED -> {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(onCompletedIconRes ?: iconRes),
                    contentDescription = stringResource(stringR.action_icon_button),
                    tint = contentColor
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun DefaultIconButtonPrev() {

    var state by remember { mutableStateOf(ButtonState.DISABLED) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            state = when (state) {
                ButtonState.DISABLED -> ButtonState.ENABLED
                ButtonState.ENABLED -> ButtonState.LOADING
                ButtonState.LOADING -> ButtonState.COMPLETED
                ButtonState.COMPLETED -> ButtonState.DISABLED
            }
        }
    }

    DefaultIconButton(
        iconRes = drawableR.ic_suggested_users_outlined_16,
        onCompletedIconRes = drawableR.ic_suggested_users_filled_16,
        state = state,
        onClick = {
            state = when (state) {
                ButtonState.DISABLED -> ButtonState.ENABLED
                ButtonState.ENABLED -> ButtonState.LOADING
                ButtonState.LOADING -> ButtonState.COMPLETED
                ButtonState.COMPLETED -> ButtonState.DISABLED
            }
        }
    )
}

