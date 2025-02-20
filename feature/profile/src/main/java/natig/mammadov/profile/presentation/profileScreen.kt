package natig.mammadov.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.theme.GradientLive
import natig.mammadov.ui_toolkit.theme.GradientScrim
import natig.mammadov.ui_toolkit.theme.GradientStory
import natig.mammadov.ui_toolkit.theme.IconVerified
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.R.drawable as drawableR

@Composable
fun ProfileScreen() {
    Column {
        ProfileTop()
    }
}

@Composable
fun ProfileTop() {
    Row (
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_left),
            contentDescription = "back"
        )
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "username",
                style = InstagramTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_verified_badge_small_16),
                contentDescription = "verified account",
                tint = IconVerified
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {                                              //TODO -> bu box olmalidi? cunki notifications gelende username saga surusur, hemde username 4dp sehvlik var (end padding 16dp)
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_options),
                contentDescription = "back"
            )
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_options),
                contentDescription = "back"
            )
        }
    }
    UserProfilePhoto()
}

@Composable
fun UserProfilePhoto() {
    Box(
        modifier = Modifier
            .size(86.dp)
            .border(width = 10.dp, brush = GradientStory, shape = CircleShape)
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen()
}