package natig.mammadov.ui_toolkit.components.buttons.pill

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import natig.mammadov.ui_toolkit.components.buttons.ButtonState
import natig.mammadov.ui_toolkit.theme.ButtonActive
import natig.mammadov.ui_toolkit.theme.ButtonDisabled
import natig.mammadov.ui_toolkit.theme.ButtonLoading
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.PillButtonPadding
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.theme.TextSemiSubtle
import natig.mammadov.ui_toolkit.R.drawable as drawableR

//TODO -> bu buttonda state ehtiyac var??
@Composable
fun ActivePillButton(
    modifier: Modifier = Modifier,
    state: ButtonState,
    onClick: (ButtonState) -> Unit,
    textEnabled: String,
    textDisabled: String? = null,
    textLoading: String? = null,
    textCompleted: String? = null,
    @DrawableRes iconRes: Int,
    @DrawableRes onCompletedIconRes: Int? = null
) {

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> ButtonDisabled
            ButtonState.ENABLED, ButtonState.COMPLETED -> ButtonActive
            ButtonState.LOADING -> ButtonLoading
        },
        label = "Active Pill button background color animation"
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> TextSemiSubtle
            ButtonState.ENABLED, ButtonState.LOADING, ButtonState.COMPLETED -> TextDefaultInverted
        },
        label = "Active Pill button content color animation"
    )

    Box(
        modifier = modifier
            .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                enabled = state != ButtonState.DISABLED && state != ButtonState.LOADING,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {
                    onClick(state)
                }
            )
            .padding(PillButtonPadding),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ButtonState.DISABLED -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = textDisabled ?: textEnabled,
                        style = InstagramTypography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.22.sp
                        ),
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(iconRes),
                        contentDescription = "Pill button action",
                        tint = contentColor
                    )
                }
            }

            ButtonState.ENABLED -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = textEnabled,
                        style = InstagramTypography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.22.sp
                        ),
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(iconRes),
                        contentDescription = "Pill button action",
                        tint = contentColor
                    )
                }
            }

            ButtonState.LOADING -> {
                if (textLoading != null) {
                    Text(
                        text = textLoading,
                        style = InstagramTypography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.22.sp
                        ),
                        color = contentColor
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier.requiredSize(18.dp),
                        strokeWidth = 2.dp,
                        strokeCap = StrokeCap.Round,
                        color = contentColor
                    )
                }
            }

            ButtonState.COMPLETED -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = textCompleted ?: textEnabled,
                        style = InstagramTypography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.22.sp
                        ),
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(onCompletedIconRes ?: iconRes),
                        contentDescription = "Pill button action",
                        tint = contentColor
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun ActivePillButtonPrev() {

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

    ActivePillButton(
        state = state,
        textEnabled = "Button",
        textLoading = "loading...",
        iconRes = drawableR.ic_next_16,
        onCompletedIconRes = drawableR.ic_suggested_users_filled_16,
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