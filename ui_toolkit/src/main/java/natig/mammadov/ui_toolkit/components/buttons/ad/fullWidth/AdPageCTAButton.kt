package natig.mammadov.ui_toolkit.components.buttons.ad.fullWidth

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import natig.mammadov.ui_toolkit.components.buttons.ButtonState
import natig.mammadov.ui_toolkit.theme.ButtonActive
import natig.mammadov.ui_toolkit.theme.ButtonDefault
import natig.mammadov.ui_toolkit.theme.ButtonDisabled
import natig.mammadov.ui_toolkit.theme.ButtonLoading
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.SquareButtonShape
import natig.mammadov.ui_toolkit.theme.TextDefault
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.theme.TextSemiSubtle
import natig.mammadov.ui_toolkit.R.drawable as drawableR
import natig.mammadov.ui_toolkit.R.string as stringR

@Composable
fun AdPageCTAButton(
    modifier: Modifier = Modifier,
    imageLink: String,
    textCallToAction: String,
    state: ButtonState,
    onClick: (ButtonState) -> Unit
) {

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> ButtonDisabled
            ButtonState.ENABLED -> ButtonActive
            ButtonState.LOADING -> ButtonLoading
            ButtonState.COMPLETED -> ButtonDefault
        },
        label = "Ad Page CTA button background color animation"
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> TextSemiSubtle
            ButtonState.ENABLED -> TextDefaultInverted
            ButtonState.LOADING -> TextDefaultInverted
            ButtonState.COMPLETED -> TextDefault
        },
        label = "Ad Page CTA content color animation"
    )


    Row(
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth()
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
            .padding(start = 6.dp, end = 12.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (state == ButtonState.LOADING) {
            CircularProgressIndicator(
                modifier = Modifier.requiredSize(24.dp),
                strokeWidth = 3.dp,
                strokeCap = StrokeCap.Round,
                color = contentColor
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = imageLink,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
                contentDescription = "Ad image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(stringR.learn_more),
                    style = InstagramTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = contentColor
                )
                Text(
                    text = textCallToAction,
                    style = InstagramTypography.bodySmall,
                    color = contentColor
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_arrow_right_16),
                contentDescription = "Call to action",
                tint = contentColor
            )
        }
    }
}


@Preview
@Composable
private fun AdPageCTAButtonPrev() {

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

    AdPageCTAButton(
        state = state,
        imageLink = "link",
        textCallToAction = "Call to action",
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