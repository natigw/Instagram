package natig.mammadov.ui_toolkit.components.buttons.square

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import natig.mammadov.ui_toolkit.components.buttons.ButtonState
import natig.mammadov.ui_toolkit.theme.ButtonDefault
import natig.mammadov.ui_toolkit.theme.ButtonDisabled
import natig.mammadov.ui_toolkit.theme.ButtonLoading
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.SquareButtonShape
import natig.mammadov.ui_toolkit.theme.TextButtonPadding
import natig.mammadov.ui_toolkit.theme.TextDefault
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.theme.TextSemiSubtle
import natig.mammadov.ui_toolkit.R.drawable as drawableR
import natig.mammadov.ui_toolkit.R.string as stringR

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    state: ButtonState,
    onClick: (ButtonState) -> Unit,
    textEnabled: String,
    textDisabled: String? = null,
    textLoading: String? = null,
    textCompleted: String? = null,
    addChevronOnCompleted: Boolean = false
) {

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> ButtonDisabled
            ButtonState.ENABLED -> ButtonDefault
            ButtonState.LOADING -> ButtonLoading
            ButtonState.COMPLETED -> ButtonDefault
        },
        label = "Default button background color animation"
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> TextSemiSubtle
            ButtonState.ENABLED -> TextDefault
            ButtonState.LOADING -> TextDefaultInverted
            ButtonState.COMPLETED -> TextDefault
        },
        label = "Default button content color animation"
    )

    Box(
        modifier = modifier
            .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
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
            .padding(TextButtonPadding),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ButtonState.DISABLED -> {
                Text(
                    text = textDisabled ?: textEnabled,
                    style = InstagramTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = contentColor
                )
            }

            ButtonState.ENABLED -> {
                Text(
                    text = textEnabled,
                    style = InstagramTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = contentColor
                )
            }

            ButtonState.LOADING -> {
                if (textLoading != null) {
                    Text(
                        text = textLoading,
                        style = InstagramTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
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
                        style = InstagramTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = contentColor
                    )
                    if (addChevronOnCompleted) {
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            modifier = Modifier.size(12.dp),
                            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_down_16),
                            contentDescription = stringResource(stringR.action_default_button),
                            tint = contentColor
                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun DefaultButtonPrev() {

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

    DefaultButton(
        textDisabled = "disabled",
        textEnabled = "Button",
        //textLoading = "loading",
        textCompleted = "successful",
        addChevronOnCompleted = true,
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

