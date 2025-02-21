package natig.mammadov.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import natig.mammadov.ui_toolkit.theme.BackgroundExclusive
import natig.mammadov.ui_toolkit.theme.BackgroundInteractive
import natig.mammadov.ui_toolkit.theme.BackgroundLive
import natig.mammadov.ui_toolkit.theme.BackgroundTranslucentInvertedSubtle
import natig.mammadov.ui_toolkit.theme.BorderCloseFriends
import natig.mammadov.ui_toolkit.theme.BorderDefault
import natig.mammadov.ui_toolkit.theme.BorderDefaultInverted
import natig.mammadov.ui_toolkit.theme.BorderStrokeDefault
import natig.mammadov.ui_toolkit.theme.BorderSubtler
import natig.mammadov.ui_toolkit.theme.GradientLive
import natig.mammadov.ui_toolkit.theme.GradientLiveSubtle
import natig.mammadov.ui_toolkit.theme.GradientScrim
import natig.mammadov.ui_toolkit.theme.GradientStory
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.theme.IconEmphasized
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.theme.TextSubtle
import kotlin.random.Random
import natig.mammadov.ui_toolkit.R.drawable as drawableR

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        TopBar()
        Stories()
        PostItemReel(
            ReelItemDto(
                profileImageUrl = "https://picsum.photos/200/300",
                username = "username",
                songTitle = "Song title",
                artistName = "Artist",
                postImageUrl = "https://picsum.photos/200/300",
                hasStory = Random.nextBoolean(),
                seenStory = Random.nextBoolean(),
                isExclusive = Random.nextBoolean()
            )
        )
    }
}

@Composable
fun TopBar() {
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
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_like_outlined),
                contentDescription = "Notifications"
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(11.dp)
                    .offset(
                        x = 1.dp,
                        y = (-1).dp
                    )  //TODO-> bu designda offset yoxdu ama qoymayanda eyni olmadi axi??
                    .background(color = IconEmphasized, shape = CircleShape)
                    .border(width = 1.5.dp, color = BorderDefaultInverted, shape = CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_share),
                contentDescription = "Share"
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 7.dp, y = (-5).dp)
                    .wrapContentWidth()
                    .height(16.dp)
                    .background(color = IconEmphasized, shape = CircleShape)
                    .padding(horizontal = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "5",
                    style = InstagramTypography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextDefaultInverted
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun Stories() {
    LazyRow(
        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
        contentPadding = PaddingValues(horizontal = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // User's Story
        item {
            StoryItem(
                StoryItemDto(
                    isUser = true,
                    username = "natig",
                    profilePicture = drawableR.ic_share,
                    storyType = StoryType.REGULAR,
                    hasAlreadySeen = true
                )
            )
        }

        // Other Users' Stories
        items(20) { index ->
            StoryItem(
                StoryItemDto(
                    isUser = false,
                    username = "user$index",
                    storyType = StoryType.entries[Random.nextInt(StoryType.entries.size)],
                    hasAlreadySeen = Random.nextBoolean()
                )
            )
        }
    }
}


@Composable
fun StoryItem(
    storyItem: StoryItemDto
) {
    Column(
        modifier = Modifier.width(78.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(78.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = if (storyItem.storyType == StoryType.LIVE_WITH_OTHERS) 0.dp  //TODO -> burda slight halqa qalir
                        else if (storyItem.hasAlreadySeen) 1.dp
                        else 3.dp,
                        brush = if (storyItem.hasAlreadySeen) SolidColor(BorderSubtler)
                        else if (storyItem.storyType == StoryType.CLOSE_FRIENDS) SolidColor(
                            BorderCloseFriends
                        )
                        else GradientStory,
                        shape = CircleShape
                    )
            ) {

                when (storyItem.storyType) {
                    StoryType.LIVE -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                                .background(color = BorderDefault, shape = CircleShape)
                                .background(brush = GradientLiveSubtle)
                                .border(width = 3.dp, color = BorderDefault, shape = CircleShape)
                                .clip(CircleShape)
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(9.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = BorderSubtler,
                                        shape = CircleShape
                                    ),
                                painter = painterResource(drawableR.ic_launcher_background),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    StoryType.LIVE_WITH_OTHERS -> {
                        MultipleLiveStory()
                    }

                    else -> {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                                .clip(CircleShape),
                            painter = painterResource(
                                storyItem.profilePicture ?: drawableR.ic_launcher_background
                            ),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            if (storyItem.storyType == StoryType.LIVE) {
                LiveBox(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 4.5.dp)
                )
            }

            if (storyItem.isUser) {
                Surface(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .align(Alignment.BottomEnd)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {

                            }
                        )
                        .border(width = 3.dp, color = BorderDefaultInverted, shape = CircleShape),
                    color = BackgroundInteractive
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        imageVector = ImageVector.vectorResource(drawableR.ic_add_16),
                        contentDescription = "Add story",
                        tint = IconDefaultInverted
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (storyItem.isUser) "Your story" else if (storyItem.storyType == StoryType.LIVE_WITH_OTHERS) "${storyItem.username} +1" else storyItem.username,
            style = if (storyItem.isUser || storyItem.hasAlreadySeen) InstagramTypography.bodySmall.copy(
                color = TextSubtle
            ) else InstagramTypography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LiveBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 27.dp, height = 15.dp)
                .clip(RoundedCornerShape(2.dp))
                .border(
                    width = 1.5.dp,
                    color = BorderDefaultInverted,
                    shape = RoundedCornerShape(2.dp)
                )
        )

        Box(
            modifier = Modifier
                .size(width = 24.dp, height = 12.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(color = BackgroundLive),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "LIVE",
                textAlign = TextAlign.Center,
                style = InstagramTypography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefaultInverted
                )
            )
        }
    }
}

@Composable
fun MultipleLiveStory() {
    Box(
        modifier = Modifier.size(78.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(64.dp)
                .border(width = 2.dp, brush = GradientStory, shape = CircleShape)
                .padding(2.dp)
                .border(width = 2.dp, color = BorderDefaultInverted, shape = CircleShape)
                .padding(2.dp)
                .border(width = 1.dp, color = BorderDefault, shape = CircleShape)
                .background(color = BorderDefault, shape = CircleShape)
                .background(brush = GradientLiveSubtle)
                .padding(7.dp)
                .background(brush = GradientLive, shape = CircleShape)
                .clip(CircleShape)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
                    .border(width = 1.dp, color = BorderSubtler, shape = CircleShape)
                    .clip(CircleShape),
                painter = painterResource(drawableR.ic_launcher_background),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(64.dp)
                .border(width = 2.dp, brush = GradientStory, shape = CircleShape)
                .padding(2.dp)
                .border(width = 2.dp, color = BorderDefaultInverted, shape = CircleShape)
                .padding(2.dp)
                .border(width = 1.dp, color = BorderDefault, shape = CircleShape)
                .background(color = BorderDefault, shape = CircleShape)
                .background(brush = GradientLiveSubtle)
                .padding(7.dp)
                .background(brush = GradientLive, shape = CircleShape)
                .clip(CircleShape)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
                    .border(width = 1.dp, color = BorderSubtler, shape = CircleShape)
                    .clip(CircleShape),
                painter = painterResource(drawableR.ic_launcher_background),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop
            )
        }
        LiveBox(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(x = 7.dp, y = 4.5.dp)
        )
    }
}


data class StoryItemDto(
    val username: String,
    @DrawableRes val profilePicture: Int? = null,
    val storyType: StoryType,
    val isUser: Boolean = false,
    val hasAlreadySeen: Boolean = false
)

enum class StoryType {
    REGULAR,
    CLOSE_FRIENDS,
    LIVE,
    LIVE_WITH_OTHERS
}


@Composable
fun PostItemReel(
    reelItem: ReelItemDto
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Log.e("Click Zone", "SAFE_ZONE -> CLICK")
                },
            model = reelItem.postImageUrl,
            contentDescription = "Reel post image",
            contentScale = ContentScale.Crop
        )

        //DEAD ZONES
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(63.dp)
                .align(Alignment.TopCenter)
                .clickable(
                    enabled = false,
                    onClick = {
                        Log.e("Click Zone", "DEAD_ZONE -> NON-CLICK")
                    }
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter)
                .clickable(
                    enabled = false,
                    onClick = {
                        Log.e("Click Zone", "DEAD_ZONE -> NON-CLICK")
                    }
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .background(brush = GradientScrim)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 6.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(34.dp)
                    .border(
                        width = if (reelItem.seenStory) 1.dp else 1.5.dp,
                        brush = if (reelItem.seenStory) SolidColor(BorderStrokeDefault) else GradientStory,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .border(width = 0.5.dp, color = BorderSubtler, shape = CircleShape)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                painter = painterResource(drawableR.ic_launcher_background),
                contentDescription = "User profile picture",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = reelItem.username,
                        style = InstagramTypography.headlineMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = TextDefaultInverted
                        )
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(drawableR.ic_verified_badge_small_16),
                        contentDescription = "verified account",
                        tint = IconDefaultInverted
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 2.dp),
                        imageVector = ImageVector.vectorResource(drawableR.ic_arrow_top_right_16),
                        contentDescription = "open song??",
                        tint = IconDefaultInverted
                    )
                    Text(
                        text = reelItem.artistName,
                        style = InstagramTypography.bodySmall.copy(color = TextDefaultInverted)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 2.dp),
                        text = "•",
                        style = InstagramTypography.labelMedium.copy(color = TextDefaultInverted)
                    )
                    Text(
                        text = reelItem.songTitle,
                        style = InstagramTypography.bodySmall.copy(color = TextDefaultInverted)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = BackgroundExclusive)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 4.dp),
                    imageVector = ImageVector.vectorResource(drawableR.ic_exclusive_16),
                    contentDescription = "Exclusive reel",
                    tint = IconDefaultInverted
                )
            }

            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_options),
                contentDescription = "Reel options",
                tint = IconDefaultInverted
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color = BackgroundTranslucentInvertedSubtle)
                    .padding(4.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_tagged_16),
                contentDescription = "Tagged users to this reel",
                tint = IconDefaultInverted
            )

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color = BackgroundTranslucentInvertedSubtle)
                    .padding(4.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_sound_on_16),
                contentDescription = "Sound on this reel",
                tint = IconDefaultInverted
            )
        }
    }
}


data class ReelItemDto(
    val profileImageUrl: String,
    val username: String,
    val songTitle: String,
    val artistName: String,
    val postImageUrl: String,
    val hasStory: Boolean,
    val seenStory: Boolean = false,
    val isExclusive: Boolean,
)

@Preview(showSystemUi = true)
@Composable
private fun HomePrev() {
    Home()
}