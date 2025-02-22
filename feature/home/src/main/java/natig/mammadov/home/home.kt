package natig.mammadov.home

import android.util.Log
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import natig.mammadov.ui_toolkit.theme.BackgroundDefault
import natig.mammadov.ui_toolkit.theme.BackgroundExclusive
import natig.mammadov.ui_toolkit.theme.BackgroundInteractive
import natig.mammadov.ui_toolkit.theme.BackgroundLive
import natig.mammadov.ui_toolkit.theme.BackgroundNotification
import natig.mammadov.ui_toolkit.theme.BackgroundSubtlerLight
import natig.mammadov.ui_toolkit.theme.BackgroundTranslucentInvertedSubtle
import natig.mammadov.ui_toolkit.theme.BorderCloseFriends
import natig.mammadov.ui_toolkit.theme.BorderDefault
import natig.mammadov.ui_toolkit.theme.BorderDefaultInverted
import natig.mammadov.ui_toolkit.theme.BorderStrokeDefault
import natig.mammadov.ui_toolkit.theme.BorderSubtleDark
import natig.mammadov.ui_toolkit.theme.BorderSubtler
import natig.mammadov.ui_toolkit.theme.GradientLive
import natig.mammadov.ui_toolkit.theme.GradientLiveSubtle
import natig.mammadov.ui_toolkit.theme.GradientScrim
import natig.mammadov.ui_toolkit.theme.GradientStory
import natig.mammadov.ui_toolkit.theme.IconDefault
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.theme.IconEmphasized
import natig.mammadov.ui_toolkit.theme.IconVerified
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextDefaultInverted
import natig.mammadov.ui_toolkit.theme.TextSubtle
import natig.mammadov.ui_toolkit.theme.TextTag
import kotlin.random.Random
import natig.mammadov.ui_toolkit.R.drawable as drawableR

@Composable
fun Home() {
    Column(
        modifier = Modifier.background(color = BackgroundDefault)
    ) {
        TopBar()
        Stories()
        Posts()
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
                contentDescription = "Notifications",
                tint = IconDefault
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(11.dp)
                    .offset(
                        x = 1.dp,
                        y = (-1).dp
                    )  //TODO-> bu designda offset yoxdu ama qoymayanda eyni olmadi axi??
                    .border(width = 1.5.dp, color = BorderDefaultInverted, shape = CircleShape)
                    .padding(1.dp)
                    .background(color = IconEmphasized, shape = CircleShape)
            )

            //TODO -> message bubble qalib duzeltmek custom shape?
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_share),
                contentDescription = "Share",
                tint = IconDefault
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 7.dp, y = (-5).dp)
                    .wrapContentWidth()
                    .height(16.dp)
                    .background(color = BackgroundNotification, shape = CircleShape)
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
                    profilePicture = "https://picsum.photos/200/300",
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
fun Posts() {
    LazyColumn {
        items(20) {
            PostItem(
                PostItemDto(
                    ratioWH = PostRatio.entries[Random.nextInt(PostRatio.entries.size)].ratio,
                    profileImageUrl = "https://picsum.photos/200/300",
                    username = "username",
                    postImageUrl = "https://picsum.photos/200/300",
                    hasStory = Random.nextBoolean(),
                    seenStory = Random.nextBoolean(),
                    isExclusive = Random.nextBoolean(),
                    isSuggestedAccount = Random.nextBoolean(),
                    country = "Baku, Azerbaijan"
                ),
                PostDetailsDto(
                    postCount = Random.nextInt(8),
                    likeCount = Random.nextInt(999999),
                    commentCount = Random.nextInt(9999),
                    shareCount = Random.nextInt(100),
                    isPostLiked = Random.nextBoolean(),
                    isPostSaved = Random.nextBoolean(),
                    userProfilePicturesLikingPost = listOf(
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300"
                    ),
                    userLikingPost = "user_your_friend",
                    profilePictureAuthor = "https://picsum.photos/200/300",
                    usernameAuthor = "author_ww_long_nameeeee_salam",
                    postDescription = "sadjnbks dasd sad ask dasdj asdk asd asd saj djsa djas dj hashjd ash dhj jh shj dasdasdasdasjd asj djas dj sajd hjfdsjhjh hja sdha sdja sjd asj",
                    usernameCommenter = "commenter_6889",
                    commentCommenter = "ekfnskdnf ksdf sdkjf sdkj fsdkjfsdfsdkf dskjf sdjkf sdkjf sdk fd fksd fksd fsd",
                    hashtagsCommenter = listOf("NatureBeauty", "hash"),
                    isCommentLiked = Random.nextBoolean(),
                    postDate = "11 January"
                )
            )
            ReelPostItem(
                ReelItemDto(
                    profileImageUrl = "https://picsum.photos/200/300",
                    username = "username",
                    songTitle = "Song title",
                    artistName = "Artist",
                    postImageUrl = "https://picsum.photos/200/300",
                    hasStory = Random.nextBoolean(),
                    seenStory = Random.nextBoolean(),
                    isExclusive = Random.nextBoolean(),
                    isSuggestedAccount = Random.nextBoolean()
                ),
                PostDetailsDto(
                    postCount = Random.nextInt(8),
                    likeCount = Random.nextInt(999999),
                    commentCount = Random.nextInt(9999),
                    shareCount = Random.nextInt(100),
                    isPostLiked = Random.nextBoolean(),
                    isPostSaved = Random.nextBoolean(),
                    userProfilePicturesLikingPost = listOf(
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300"
                    ),
                    userLikingPost = "user_your_friend",
                    profilePictureAuthor = "https://picsum.photos/200/300",
                    usernameAuthor = "author_ww_long_nameeeee_salam",
                    postDescription = "sadjnbks dasd sad ask dasdj asdk asd asd saj djsa djas dj hashjd ash dhj jh shj dasdasdasdasjd asj djas dj sajd hjfdsjhjh hja sdha sdja sjd asj",
                    usernameCommenter = "commenter_6889",
                    commentCommenter = "ekfnskdnf ksdf sdkjf sdkj fsdkjfsdfsdkf dskjf sdjkf sdkjf sdk fd fksd fksd fsd",
                    hashtagsCommenter = listOf("NatureBeauty", "hash"),
                    isCommentLiked = Random.nextBoolean(),
                    postDate = "11 January"
                )
            )
            AdPostItem(
                AdPostItemDto(
                    ratioWH = PostRatio.entries[Random.nextInt(PostRatio.entries.size)].ratio,
                    profileImageUrl = "https://picsum.photos/200/300",
                    username = "username",
                    postImageUrl = "https://picsum.photos/200/300",
                    hasStory = Random.nextBoolean(),
                    seenStory = Random.nextBoolean()
                ),
                PostDetailsDto(
                    postCount = Random.nextInt(8),
                    likeCount = Random.nextInt(999999),
                    commentCount = Random.nextInt(9999),
                    shareCount = Random.nextInt(100),
                    isPostLiked = Random.nextBoolean(),
                    isPostSaved = Random.nextBoolean(),
                    userProfilePicturesLikingPost = listOf(
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300"
                    ),
                    userLikingPost = "user_your_friend",
                    profilePictureAuthor = "https://picsum.photos/200/300",
                    usernameAuthor = "author_ww_long_nameeeee_salam",
                    postDescription = "sadjnbks dasd sad ask dasdj asdk asd asd saj djsa djas dj hashjd ash dhj jh shj dasdasdasdasjd asj djas dj sajd hjfdsjhjh hja sdha sdja sjd asj",
                    usernameCommenter = "commenter_6889",
                    commentCommenter = "ekfnskdnf ksdf sdkjf sdkj fsdkjfsdfsdkf dskjf sdjkf sdkjf sdk fd fksd fksd fsd",
                    hashtagsCommenter = listOf("NatureBeauty", "hash"),
                    isCommentLiked = Random.nextBoolean(),
                    postDate = "11 January"
                )
            )
            PostItem(
                PostItemDto(
                    ratioWH = PostRatio.entries[Random.nextInt(PostRatio.entries.size)].ratio,
                    profileImageUrl = "https://picsum.photos/200/300",
                    username = "username",
                    postImageUrl = "https://picsum.photos/200/300",
                    hasStory = Random.nextBoolean(),
                    seenStory = Random.nextBoolean(),
                    isExclusive = Random.nextBoolean(),
                    country = "Baku, Azerbaijan",
                    isSuggestedAccount = true
                ),
                PostDetailsDto(
                    postCount = Random.nextInt(8),
                    likeCount = Random.nextInt(999999),
                    commentCount = Random.nextInt(9999),
                    shareCount = Random.nextInt(100),
                    isPostLiked = Random.nextBoolean(),
                    isPostSaved = Random.nextBoolean(),
                    userProfilePicturesLikingPost = listOf(
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300",
                        "https://picsum.photos/200/300"
                    ),
                    userLikingPost = "user_your_friend",
                    profilePictureAuthor = "https://picsum.photos/200/300",
                    usernameAuthor = "author_ww_long_nameeeee_salam",
                    postDescription = "sadjnbks dasd sad ask dasdj asdk asd asd saj djsa djas dj hashjd ash dhj jh shj dasdasdasdasjd asj djas dj sajd hjfdsjhjh hja sdha sdja sjd asj",
                    usernameCommenter = "commenter_6889",
                    commentCommenter = "ekfnskdnf ksdf sdkjf sdkj fsdkjfsdfsdkf dskjf sdjkf sdkjf sdk fd fksd fksd fsd",
                    hashtagsCommenter = listOf("NatureBeauty", "hash"),
                    isCommentLiked = Random.nextBoolean(),
                    postDate = "11 January"
                )
            )
            SuggestedReels()
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
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(9.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = BorderSubtler,
                                        shape = CircleShape
                                    )
                                    .padding(0.5.dp),
                                model = storyItem.profilePicture,
                                placeholder = painterResource(drawableR.ic_highlight_slides),
                                error = painterResource(drawableR.ic_launcher_background),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    StoryType.LIVE_WITH_OTHERS -> {
                        MultipleLiveStory(
                            StoryItemDto(
                                storyType = StoryType.LIVE_WITH_OTHERS,
                                username = "multiUser"
                            )
                        )
                    }

                    else -> {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                                .clip(CircleShape),
                            model = storyItem.profilePicture,
                            placeholder = painterResource(drawableR.ic_highlight_slides),
                            error = painterResource(drawableR.ic_launcher_background),
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
                        .align(Alignment.BottomEnd)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {

                            }
                        )
                        .border(width = 3.dp, color = BorderDefaultInverted, shape = CircleShape)
                        .padding(3.dp)
                        .clip(CircleShape),
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
fun MultipleLiveStory(
    storyItem: StoryItemDto
) {
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
                .background(brush = GradientLiveSubtle, shape = CircleShape)
                .padding(7.dp)
                .background(brush = GradientLive, shape = CircleShape)
                .clip(CircleShape)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
                    .border(width = 1.dp, color = BorderSubtler, shape = CircleShape)
                    .padding(0.5.dp)
                    .clip(CircleShape),
                model = storyItem.profilePicture,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
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
                .background(brush = GradientLiveSubtle, shape = CircleShape)
                .padding(7.dp)
                .background(brush = GradientLive, shape = CircleShape)
                .clip(CircleShape)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
                    .border(width = 1.dp, color = BorderSubtler, shape = CircleShape)
                    .padding(0.5.dp)
                    .clip(CircleShape),
                model = storyItem.profilePicture,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
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
    val profilePicture: String? = null,
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


enum class PostRatio(val ratio: Float) {
    PORTRAIT(0.8f),   // 4:5
    SQUARE(1f),     // 1:1
    LANDSCAPE(1.91f); // 1.91:1
}


@Composable
fun ReelPostItem(
    reelItem: ReelItemDto,
    postDetails: PostDetailsDto
) {
    Column {
        if (reelItem.isSuggestedAccount) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = buildAnnotatedString {
                        withStyle(style = InstagramTypography.headlineMedium.toSpanStyle()) {
                            append("Because you watched a reel from ")
                        }
                        withStyle(
                            style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                                .toSpanStyle()
                        ) {
                            append(reelItem.username)
                        }
                    }
                )
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {

                            }
                        ),
                    imageVector = ImageVector.vectorResource(drawableR.ic_close_16),
                    contentDescription = "Close suggested post",
                    tint = IconDefault
                )
            }
        }
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
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
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
                AsyncImage(
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
                    model = reelItem.profileImageUrl,
                    placeholder = painterResource(drawableR.ic_highlight_slides),
                    error = painterResource(drawableR.ic_launcher_background),
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

                if (reelItem.isExclusive) {
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

            //TODO -> bunu deqiqlesdir gorek burda cox reel olanda bu lazim olurmu
//            Text(
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(top = 52.dp, end = 12.dp)
//                    .background(color = BackgroundTranslucentInvertedSubtle, shape = CircleShape)
//                    .padding(horizontal = 6.dp, vertical = 4.dp),
//                text = "1/5",
//                style = InstagramTypography.bodySmall.copy(color = TextDefaultInverted)
//            )

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

        PostDetails(postDetails)
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
    val isSuggestedAccount: Boolean
)

@Composable
fun PostItem(
    postItem: PostItemDto,
    postDetails: PostDetailsDto
) {
    Column {
        if (postItem.isSuggestedAccount) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = buildAnnotatedString {
                        withStyle(style = InstagramTypography.headlineMedium.toSpanStyle()) {
                            append("Because you like a post from ")
                        }
                        withStyle(
                            style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                                .toSpanStyle()
                        ) {
                            append(postItem.username)
                        }
                    }
                )
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {

                            }
                        ),
                    imageVector = ImageVector.vectorResource(drawableR.ic_close_16),
                    contentDescription = "Close suggested post",
                    tint = IconDefault
                )
            }
        } else HorizontalDivider(thickness = 1.dp, color = BorderSubtler)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 8.dp, top = 7.dp, bottom = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(34.dp)
                    .border(
                        width = if (postItem.seenStory) 1.dp else 1.5.dp,
                        brush = if (postItem.seenStory) SolidColor(BorderStrokeDefault) else GradientStory,
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
                model = postItem.profileImageUrl,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
                contentDescription = "User profile picture",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = postItem.username,
                        style = InstagramTypography.headlineMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(drawableR.ic_verified_badge_small_16),
                        contentDescription = "verified account",
                        tint = IconVerified
                    )
                }

                postItem.country?.let {
                    Text(
                        text = it,
                        style = InstagramTypography.bodySmall
                    )
                }
            }

            if (postItem.isExclusive) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = BackgroundExclusive)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            start = 4.dp,
                            end = 6.dp,
                            top = 2.dp,
                            bottom = 2.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(drawableR.ic_exclusive_16),
                            contentDescription = "Exclusive reel",
                            tint = IconDefaultInverted
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Exclusive",
                            style = InstagramTypography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextDefaultInverted
                            )
                        )
                    }
                }
            }

            if (postItem.isSuggestedAccount) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(color = BackgroundSubtlerLight)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            end = 8.dp,
                            top = 6.dp,
                            bottom = 6.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Follow",
                            style = InstagramTypography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            modifier = Modifier.size(12.dp),
                            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_down_16),
                            contentDescription = "Exclusive reel",
                            tint = IconDefault
                        )
                    }
                }
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
                contentDescription = "Post options",
                tint = IconDefault
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(postItem.ratioWH)
                    .fillMaxWidth(),
                model = postItem.postImageUrl,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
                contentDescription = "Reel post image",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
                    .background(color = BackgroundTranslucentInvertedSubtle, shape = CircleShape)
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                text = "2/5",
                style = InstagramTypography.bodySmall.copy(color = TextDefaultInverted)
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
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
                contentDescription = "Tagged users to this post",
                tint = IconDefaultInverted
            )
        }

        PostDetails(postDetails)
    }
}

data class PostItemDto(
    val ratioWH: Float,  // 0.8f for 4:5 ratio
    val profileImageUrl: String,
    val username: String,
    val country: String?,
    val postImageUrl: String,
    val hasStory: Boolean,
    val seenStory: Boolean,
    val isExclusive: Boolean,
    val isSuggestedAccount: Boolean
)


@Composable
fun AdPostItem(
    adPostItem: AdPostItemDto,
    postDetails: PostDetailsDto
) {
    Column {
        HorizontalDivider(thickness = 1.dp, color = BorderSubtler)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 8.dp, top = 7.dp, bottom = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(34.dp)
                    .border(
                        width = if (adPostItem.seenStory) 1.dp else 1.5.dp,
                        brush = if (adPostItem.seenStory) SolidColor(BorderStrokeDefault) else GradientStory,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .border(width = 0.5.dp, color = BorderSubtler, shape = CircleShape)
                    .padding(0.25.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                model = adPostItem.profileImageUrl,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
                contentDescription = "User profile picture",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = adPostItem.username,
                        style = InstagramTypography.headlineMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(drawableR.ic_verified_badge_small_16),
                        contentDescription = "verified account",
                        tint = IconVerified
                    )
                }

                Text(
                    text = "Sponsored",
                    style = InstagramTypography.bodySmall
                )
            }

            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = BackgroundSubtlerLight)
            ) {
                Row(
                    modifier = Modifier.padding(
                        start = 12.dp,
                        end = 8.dp,
                        top = 6.dp,
                        bottom = 6.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Follow",
                        style = InstagramTypography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = ImageVector.vectorResource(drawableR.ic_arrow_down_16),
                        contentDescription = "Exclusive reel",
                        tint = IconDefault
                    )
                }
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
                contentDescription = "Post options",
                tint = IconDefault
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(adPostItem.ratioWH)
                    .fillMaxWidth(),
                model = adPostItem.postImageUrl,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
                contentDescription = "Reel post image",
                contentScale = ContentScale.Crop
            )


            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
                    .background(color = BackgroundTranslucentInvertedSubtle, shape = CircleShape)
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                text = "1/5",
                style = InstagramTypography.bodySmall.copy(color = TextDefaultInverted)
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
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
                contentDescription = "Tagged users to this post",
                tint = IconDefaultInverted
            )
        }

        Row(
            modifier = Modifier
                .background(BackgroundInteractive)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Call to action",
                style = InstagramTypography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefaultInverted
                )
            )
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_arrow_right),
                contentDescription = "Proceed call to action",
                tint = IconDefaultInverted
            )
        }

        PostDetails(postDetails)
    }
}

data class AdPostItemDto(
    val ratioWH: Float,  // 0.8f for 4:5 ratio
    val profileImageUrl: String,
    val username: String,
    val postImageUrl: String,
    val hasStory: Boolean,
    val seenStory: Boolean
)


@Composable
fun PostDetails(
    postDetails: PostDetailsDto
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {

        //TODO- > carousel for multiple posts

        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
//                modifier = Modifier
                //TODO -> butun buttonlara click qoy
//                    .clickable(
//                        interactionSource = remember { MutableInteractionSource() },
//                        indication = rememberRipple(bounded = false),
//                        onClick = {
//
//                        }
//                    ),
                imageVector = ImageVector.vectorResource(if (postDetails.isPostLiked) drawableR.ic_like_filled else drawableR.ic_like_outlined),
                contentDescription = "Like post",
                tint = if (postDetails.isPostLiked) IconEmphasized else IconDefault
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = postDetails.likeCount.toString(),
                style = InstagramTypography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_comment),
                contentDescription = "Comment on post",
                tint = IconDefault
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = postDetails.commentCount.toString(),
                style = InstagramTypography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_share),
                contentDescription = "Share post",
                tint = IconDefault
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = postDetails.shareCount.toString(),
                style = InstagramTypography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(if (postDetails.isPostSaved) drawableR.ic_save_filled else drawableR.ic_save_outlined),
                contentDescription = "Save post",
                tint = IconDefault
            )
        }

        Row(
            modifier = Modifier
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .offset(x = (-2).dp)
                    .width(42.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(20.dp)
                        .border(
                            width = 2.dp,
                            color = BorderDefaultInverted,
                            shape = CircleShape
                        )
                        .padding(2.dp)
                        .border(
                            width = 0.5.dp,
                            color = BorderSubtler,
                            shape = CircleShape
                        )
                        .padding(0.5.dp)
                        .clip(CircleShape),
                    model = postDetails.userProfilePicturesLikingPost[0],
                    placeholder = painterResource(drawableR.ic_highlight_slides),
                    error = painterResource(drawableR.ic_launcher_background),
                    contentDescription = "Users liking this post",
                    contentScale = ContentScale.Crop
                )
                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(20.dp)
                        .border(
                            width = 2.dp,
                            color = BorderDefaultInverted,
                            shape = CircleShape
                        )
                        .padding(2.dp)
                        .border(
                            width = 0.5.dp,
                            color = BorderSubtler,
                            shape = CircleShape
                        )
                        .padding(0.5.dp)
                        .clip(CircleShape),
                    model = postDetails.userProfilePicturesLikingPost[1],
                    placeholder = painterResource(drawableR.ic_highlight_slides),
                    error = painterResource(drawableR.ic_launcher_background),
                    contentDescription = "Users liking this post",
                    contentScale = ContentScale.Crop
                )
                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(20.dp)
                        .border(
                            width = 2.dp,
                            color = BorderDefaultInverted,
                            shape = CircleShape
                        )
                        .padding(2.dp)
                        .border(
                            width = 0.5.dp,
                            color = BorderSubtler,
                            shape = CircleShape
                        )
                        .padding(0.5.dp)
                        .clip(CircleShape),
                    model = postDetails.userProfilePicturesLikingPost[2],
                    placeholder = painterResource(drawableR.ic_highlight_slides),
                    error = painterResource(drawableR.ic_launcher_background),
                    contentDescription = "Users liking this post",
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = buildAnnotatedString {
                    withStyle(style = InstagramTypography.bodyLarge.toSpanStyle()) {
                        append("Liked by ")
                    }
                    withStyle(
                        style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                            .toSpanStyle()
                    ) {
                        append(postDetails.userLikingPost)
                    }
                    withStyle(style = InstagramTypography.bodyLarge.toSpanStyle()) {
                        append(" and ")
                    }
                    withStyle(
                        style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                            .toSpanStyle()
                    ) {
                        append("${postDetails.likeCount - 1} others")
                    }
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        //TODO -> bu hisseni nece edim: username [4.dp space] description1, description2... [more]
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                        .toSpanStyle()
                ) {
                    append(postDetails.usernameAuthor)
                }
                withStyle(style = InstagramTypography.bodyLarge.toSpanStyle()) {
                    append(postDetails.postDescription)
                }
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "View all ${postDetails.commentCount} comments",
            style = InstagramTypography.bodyLarge.copy(color = TextSubtle)
        )

        Row(
            modifier = Modifier
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = buildAnnotatedString {
                    withStyle(
                        style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                            .toSpanStyle()
                    ) {
                        append(postDetails.usernameCommenter)
                    }
                    //TODO -> space between username and comment
                    withStyle(style = InstagramTypography.bodyLarge.toSpanStyle()) {
                        append(postDetails.commentCommenter)
                    }
                    //TODO -> space before hashtags
                    withStyle(
                        style = InstagramTypography.bodyLarge.copy(color = TextTag).toSpanStyle()
                    ) {
                        postDetails.hashtagsCommenter?.forEach {
                            append("#$it")
                        }
                    }
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = ImageVector.vectorResource(if (postDetails.isCommentLiked) drawableR.ic_like_filled_16 else drawableR.ic_like_outlined_16),
                contentDescription = "Like comment",
                tint = if (postDetails.isCommentLiked) IconEmphasized else IconDefault
            )
        }

        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        width = 0.5.dp,
                        color = BorderSubtler,
                        shape = CircleShape
                    )
                    .padding(0.25.dp)
                    .clip(CircleShape),
                model = postDetails.profilePictureAuthor,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
                contentDescription = "Your profile picture",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add a comment...",
                style = InstagramTypography.bodyLarge.copy(color = TextSubtle)
            )
        }

        Text(
            text = postDetails.postDate,
            style = InstagramTypography.bodySmall.copy(color = TextSubtle)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}


data class PostDetailsDto(
    val postCount: Int,
    val likeCount: Int,
    val commentCount: Int,
    val shareCount: Int,
    val isPostLiked: Boolean,
    val isPostSaved: Boolean,
    val userProfilePicturesLikingPost: List<String>,
    val userLikingPost: String,
    val profilePictureAuthor: String,
    val usernameAuthor: String,
    val postDescription: String,
    val usernameCommenter: String,
    val commentCommenter: String,
    val hashtagsCommenter: List<String>?,
    val isCommentLiked: Boolean,
    val postDate: String
)


@Composable
fun SuggestedReels() {
    Column {
        Text(
            modifier = Modifier
                .padding(12.dp),
            text = "Suggested reels",
            style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(20) {
                ReelPortraitItem(
                    imageLink = "https://picsum.photos/900/1600"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ReelPortraitItem(
    imageLink: String
) {
    AsyncImage(
        modifier = Modifier
            .size(width = 110.dp, height = 194.dp)
            .clip(RoundedCornerShape(4.dp)),
        model = imageLink,
        placeholder = painterResource(drawableR.ic_highlight_slides),
        error = painterResource(drawableR.ic_launcher_background),
        contentDescription = "Reel",
        contentScale = ContentScale.Crop
    )
}


@Composable
fun LiveStory(modifier: Modifier = Modifier) {  //TODO -> bu en yaxsi versiyadi esas yerlerde evez et
    AsyncImage(
        modifier = Modifier
            .size(78.dp) //fillMaxSize()
            .border(width = 3.dp, brush = GradientStory, shape = CircleShape)
            .padding(5.dp)
            .border(width = 3.dp, color = BorderSubtleDark, shape = CircleShape)
            .background(color = BorderDefault, shape = CircleShape)
            .background(brush = GradientLiveSubtle, shape = CircleShape)
            .clip(CircleShape)
            .padding(9.dp)
            .border(
                width = 1.dp,
                color = BorderSubtler,
                shape = CircleShape
            )
            .padding(0.5.dp)
            .clip(CircleShape),
        model = "Sad",
        placeholder = painterResource(drawableR.ic_highlight_slides),
        error = painterResource(drawableR.ic_launcher_background),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop
    )
}

@Preview(showSystemUi = true)
@Composable
private fun HomePrev() {
    Home()
}