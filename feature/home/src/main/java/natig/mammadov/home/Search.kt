package natig.mammadov.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import natig.mammadov.ui_toolkit.components.buttons.ButtonState
import natig.mammadov.ui_toolkit.components.buttons.ad.fullWidth.ActiveFullWidthSquareAdButton
import natig.mammadov.ui_toolkit.theme.BackgroundDefault
import natig.mammadov.ui_toolkit.theme.BackgroundSubtlerLight
import natig.mammadov.ui_toolkit.theme.BackgroundTranslucentInverted
import natig.mammadov.ui_toolkit.theme.BorderSubtler
import natig.mammadov.ui_toolkit.theme.GradientScrim
import natig.mammadov.ui_toolkit.theme.IconDefault
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.theme.IconSubtle
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.theme.TextSubtle
import natig.mammadov.ui_toolkit.theme.TextTranslucent
import kotlin.random.Random
import natig.mammadov.ui_toolkit.R.drawable as drawableR

@Composable
fun SearchScreen() {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            SearchBox()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = BackgroundDefault)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(11) {
                    BigSponsoredPost(
                        ExploreItemDto(
                            ratio = ExploreRatio.BIG_SQUARE,
                            imageLink = "asum.photos/900/1600",
                            isMultiPost = Random.nextBoolean(),
                            isReel = Random.nextBoolean(),
                            isSponsored = Random.nextBoolean(),
                            username = "username",
                            profilePicture = "asum.photos/900/1600"
                        )
                    )
                }
            }

//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//        verticalArrangement = Arrangement.spacedBy(1.dp),
//        horizontalArrangement = Arrangement.spacedBy(1.dp),
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(11) {
//            ExplorePost(
//                ExploreItemDto(
//                    ratio = ExploreRatio.SQUARE,
//                    imageLink = "asum.photos/900/1600",
//                    isMultiPost = Random.nextBoolean(),
//                    isReel = Random.nextBoolean(),
//                    isSponsored = Random.nextBoolean(),
//                    username = "username",
//                    profilePicture = "asum.photos/900/1600"
//                )
//            )
//        }
//    }
        }
    }
}

@Composable
fun SearchBox() {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(color = BackgroundSubtlerLight, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(drawableR.ic_search_16),
                    contentDescription = "Search",
                    tint = IconSubtle
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Search",
                    style = InstagramTypography.titleMedium.copy(color = TextSubtle),
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {

                    }
                ),
            imageVector = ImageVector.vectorResource(drawableR.ic_options_16),
            contentDescription = "Search options",
            tint = IconDefault
        )
    }
}

@Composable
fun ExplorePost(
    postItem: ExploreItemDto
) {
    Box(
        modifier = Modifier.aspectRatio(postItem.ratio.ratio)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {

                    }
                ),
            model = postItem.imageLink,
            placeholder = painterResource(drawableR.ic_highlight_slides),
            error = painterResource(drawableR.ic_launcher_background),
            contentDescription = "Post image",
            contentScale = ContentScale.Crop
        )
        if (postItem.isSponsored.not()) {
            Icon(
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp)
                    .align(Alignment.TopEnd)
                    .graphicsLayer(
                        shadowElevation = with(LocalDensity.current) { 8.dp.toPx() },
                        shape = CircleShape
                    ),
                imageVector = ImageVector.vectorResource(if (postItem.isMultiPost) drawableR.ic_gallery_16 else drawableR.ic_reel_16),
                contentDescription = "Multiple posts",
                tint = IconDefaultInverted
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = GradientScrim)
                    .padding(6.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    text = "Sponsored",
                    style = InstagramTypography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextDefaultInverted
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(22.dp)
                        .clip(CircleShape)
                        .border(width = 0.5.dp, color = BorderSubtler, shape = CircleShape),
                    model = postItem.profilePicture,
                    placeholder = painterResource(drawableR.ic_highlight_slides),
                    error = painterResource(drawableR.ic_launcher_background),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun BigSponsoredPost(
    postItem: ExploreItemDto
) {
    Box(
        modifier = Modifier.aspectRatio(ExploreRatio.BIG_SQUARE.ratio)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {

                    }
                ),
            model = postItem.imageLink,
            placeholder = painterResource(drawableR.ic_highlight_slides),
            error = painterResource(drawableR.ic_launcher_background),
            contentDescription = "Post image",
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .background(brush = GradientScrim)
                .padding(start = 8.dp, end = 12.dp, top = 8.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = BackgroundTranslucentInverted,
                        shape = CircleShape
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = buildAnnotatedString {
                    withStyle(
                        style = InstagramTypography.bodySmall.copy(color = TextDefaultInverted)
                            .toSpanStyle()
                    ) {
                        //TODO -> username cox uzun olsa nece olacaq? overflow ellipsize??
                        append(postItem.username)
                    }
                    withStyle(
                        style = InstagramTypography.bodySmall.copy(color = TextTranslucent)
                            .toSpanStyle()
                    ) {
                        append(" • Sponsored")
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .graphicsLayer(
                        shadowElevation = with(LocalDensity.current) { 8.dp.toPx() },
                        shape = CircleShape
                    ),
                imageVector = ImageVector.vectorResource(if (postItem.isMultiPost) drawableR.ic_gallery_16 else drawableR.ic_reel_16),
                contentDescription = "Multiple posts",
                tint = IconDefaultInverted
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .graphicsLayer(
                        shadowElevation = with(LocalDensity.current) { 8.dp.toPx() },
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_options_16),
                contentDescription = "Sponsored post options",
                tint = IconDefaultInverted
            )
        }

        ActiveFullWidthSquareAdButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            textCallToAction = "Call to action",
            state = ButtonState.ENABLED,
            onClick = {

            }
        )
    }
}

data class ExploreItemDto(
    val ratio: ExploreRatio,
    val imageLink: String,
    val isMultiPost: Boolean,
    val isReel: Boolean,
    val isSponsored: Boolean,
    val username: String,
    val profilePicture: String
)

enum class ExploreRatio(val ratio: Float) {
    SQUARE(1f),     // 1:1
    BIG_SQUARE(1f), // 1:1
    LONG_REEL(0.496f) //124:250
}

@Preview(showSystemUi = true)
@Composable
private fun SearchScreenPrev() {
    SearchScreen()
}