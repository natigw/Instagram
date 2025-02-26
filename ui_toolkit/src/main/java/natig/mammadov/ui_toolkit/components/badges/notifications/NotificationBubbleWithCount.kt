package natig.mammadov.ui_toolkit.components.badges.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.theme.BackgroundNotification
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted

@Composable
fun NotificationBubbleWithCount(
    modifier: Modifier = Modifier,
    count: Int
) {
    Text(
        modifier = modifier
            .background(color = BackgroundNotification, shape = CircleShape)
            .padding(horizontal = 6.dp, vertical = 1.dp),
        text = count.toString(),
        style = InstagramTypography.labelMedium.copy(
            fontWeight = FontWeight.SemiBold,
            color = TextDefaultInverted
        )
    )
}

@Preview
@Composable
private fun NotificationBubbleWithCountPrev() {
    NotificationBubbleWithCount(count = 1)
}