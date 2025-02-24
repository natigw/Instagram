package natig.mammadov.ui_toolkit.components.badges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.theme.BackgroundCloseFriends
import natig.mammadov.ui_toolkit.theme.BadgeShape
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.R.drawable as drawableR
import natig.mammadov.ui_toolkit.R.string as stringR

@Composable
fun CloseFriendsBadge(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(BadgeShape)
            .background(color = BackgroundCloseFriends)
            .padding(vertical = 2.dp, horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(drawableR.ic_star_16),
            contentDescription = stringResource(stringR.close_friends_post),
            tint = IconDefaultInverted
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            modifier = Modifier.size(8.dp),
            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_down_16),
            contentDescription = stringResource(stringR.close_friends_badge_action),
            tint = IconDefaultInverted
        )
    }
}

@Preview
@Composable
private fun CloseFriendsBadgePrev() {
    CloseFriendsBadge()
}