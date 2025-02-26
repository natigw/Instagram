package natig.mammadov.ui_toolkit.components.badges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.theme.BackgroundExclusive
import natig.mammadov.ui_toolkit.theme.BadgeShape
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.R.drawable as drawableR
import natig.mammadov.ui_toolkit.R.string as stringR

@Composable
fun ExclusiveBadge(
    modifier: Modifier = Modifier,
    withText: Boolean = false
) {
    Row(
        modifier = modifier
            .clip(BadgeShape)
            .background(color = BackgroundExclusive)
            .padding(vertical = 2.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(drawableR.ic_exclusive_16),
            contentDescription = stringResource(stringR.exclusive_post),
            tint = IconDefaultInverted
        )
        if (withText) {  //TODO -> bele yaxsidi yoxsa withText ucun yeni component yazmaq (row da var)
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 2.dp),
                text = stringResource(stringR.exclusive_badge),
                style = InstagramTypography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefaultInverted
                )
            )
        }
    }
}

@Preview
@Composable
private fun ExclusiveBadgePrev() {
    Column {
        ExclusiveBadge()
        Spacer(Modifier.height(4.dp))
        ExclusiveBadge(withText = true)
    }
}