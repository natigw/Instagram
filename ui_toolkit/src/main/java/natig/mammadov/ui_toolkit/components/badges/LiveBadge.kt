package natig.mammadov.ui_toolkit.components.badges

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.theme.BackgroundLive
import natig.mammadov.ui_toolkit.theme.BadgeShape
import natig.mammadov.ui_toolkit.theme.BorderDefaultInverted
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.SmallBadgeShape
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.R.string as stringR

@Composable
fun LiveBadge(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .clip(SmallBadgeShape)
            .background(color = BackgroundLive)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        text = stringResource(stringR.live_badge),
        style = InstagramTypography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            color = TextDefaultInverted
        )
    )
}

@Composable
fun LiveBadgeWithStroke(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .border(
                width = 1.5.dp,
                color = BorderDefaultInverted,
                shape = BadgeShape
            )
            .padding(1.5.dp)
            .background(color = BackgroundLive)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        text = stringResource(stringR.live_badge),
        style = InstagramTypography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            color = TextDefaultInverted
        )
    )
}

@Preview
@Composable
private fun LiveBadgePrev() {
    Column {
        LiveBadge()
        LiveBadgeWithStroke()
    }
}

