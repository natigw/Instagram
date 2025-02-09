package natig.mammadov.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import natig.mammadov.ui_toolkit.R.drawable as drawableR

@Composable
fun Home() {
    Column {
        NavigationBar()
        Stories()
    }
}

@Composable
fun NavigationBar() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(top = 8.dp, bottom = 4.dp),
            imageVector = ImageVector.vectorResource(drawableR.logo_instagram),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            imageVector = ImageVector.vectorResource(drawableR.ic_like_outlined),
            contentDescription = "Notifications"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            imageVector = ImageVector.vectorResource(drawableR.ic_share),
            contentDescription = "Share"
        )
    }
}

@Composable
fun Stories() {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .border(width = 1.dp, color = Color.Green, shape = CircleShape)
        ) {

        }
    }
}


@Preview(showSystemUi = true, apiLevel = 33)
@Composable
private fun HomePrev() {
    Home()
}