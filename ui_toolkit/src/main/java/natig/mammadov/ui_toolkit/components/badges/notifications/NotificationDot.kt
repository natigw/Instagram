package natig.mammadov.ui_toolkit.components.badges.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.theme.BorderDefaultInverted
import natig.mammadov.ui_toolkit.theme.IconEmphasized

@Composable
fun NotificationDot(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color = IconEmphasized)
    )
}

@Composable
fun NotificationDotWithBorder(modifier: Modifier = Modifier) {
    NotificationDot(
        modifier = modifier
            .offset(           //extra offset because of outside border
                x = 1.5.dp,
                y = (-1.5).dp
            )
            .border(width = 1.5.dp, color = BorderDefaultInverted, shape = CircleShape)
            .padding(1.5.dp)
    )
}

@Preview
@Composable
private fun NotificationDotPrev() {
    Column {
        NotificationDot()
        NotificationDotWithBorder()
    }
}