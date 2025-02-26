package natig.mammadov.ui_toolkit.components.badges.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.theme.BackgroundNotification
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.R.drawable as drawableR

@Composable
fun NotificationGroupBubbleWithCount(
    modifier: Modifier = Modifier,
    likeCount: Int = 0,
    commentCount: Int = 0
) {
    Row(
        modifier = modifier
            .height(32.dp)
            .background(color = BackgroundNotification, shape = CircleShape)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (likeCount != 0) { //TODO -> burda unsigned int islenmelidi data type olaraq? menfi ededleri vere bilmesin
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(drawableR.ic_like_filled_16),
                    contentDescription = "Like notifications",
                    tint = IconDefaultInverted
                )
                Text(
                    text = likeCount.toString(),
                    style = InstagramTypography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextDefaultInverted
                    )
                )
            }
        }

        if (commentCount != 0) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(drawableR.ic_comment_filled_16),
                    contentDescription = "Comment notifications",
                    tint = IconDefaultInverted
                )
                Text(
                    text = commentCount.toString(),
                    style = InstagramTypography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextDefaultInverted
                    )
                )
            }
        }
    }
}


////TODO -> daha yaxsi ne usul etmek olar?
//enum class NotificationType {
//    LIKE,
//    COMMENT,
//    SHARE,
//    THREADS // TODO -> bunu ozumden yazdim??
//}
//
//data class NotificationTypeWithCount(
//    val type: NotificationType,
//    val count: Int
//)

@Preview
@Composable
private fun NotificationGroupBubbleWithCountPrev() {
    NotificationGroupBubbleWithCount(
        likeCount = 6,
        commentCount = 31
    )
}