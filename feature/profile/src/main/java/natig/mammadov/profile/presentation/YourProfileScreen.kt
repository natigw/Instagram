package natig.mammadov.profile.presentation

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
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
import natig.mammadov.ui_toolkit.components.buttons.ButtonState
import natig.mammadov.ui_toolkit.components.buttons.circle.CircleIconButton
import natig.mammadov.ui_toolkit.components.buttons.square.DefaultButton
import natig.mammadov.ui_toolkit.components.badges.notifications.NotificationBubbleWithCount
import natig.mammadov.ui_toolkit.components.badges.notifications.NotificationDotWithBorder
import natig.mammadov.ui_toolkit.components.badges.notifications.ThreadsNotificationDot
import natig.mammadov.ui_toolkit.theme.BackgroundDefault
import natig.mammadov.ui_toolkit.theme.BackgroundInteractive
import natig.mammadov.ui_toolkit.theme.BackgroundSubtlerLight
import natig.mammadov.ui_toolkit.theme.BorderCloseFriends
import natig.mammadov.ui_toolkit.theme.BorderDefault
import natig.mammadov.ui_toolkit.theme.BorderDefaultInverted
import natig.mammadov.ui_toolkit.theme.BorderHighlight
import natig.mammadov.ui_toolkit.theme.BorderSubtler
import natig.mammadov.ui_toolkit.theme.GradientStory
import natig.mammadov.ui_toolkit.theme.IconDefault
import natig.mammadov.ui_toolkit.theme.IconDefaultInverted
import natig.mammadov.ui_toolkit.theme.IconSubtle
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextSubtle
import natig.mammadov.ui_toolkit.theme.TextTag
import natig.mammadov.ui_toolkit.R.drawable as drawableR

@Composable
fun YourProfileScreen() {
    Column(
        modifier = Modifier.background(BackgroundDefault)
    ) {
        YourProfileTopBar(username = "username")
        YourProfileInfo(
            profileInfoItem = ProfileInfoItemDto(
                profilePicture = "https://picsum.photos/200/300",
                storyType = StoryType.REGULAR,
                hasAlreadySeen = false,
                postCount = 151,
                followerCount = 112,
                followingCount = 62
            )
        )
        YourProfileBio(
            bioItem = ProfileBioItemDto(
                username = "gursky.studio",
                fullName = "Natig Mammadov",
                pronoun = Pronoun.HE,
                threadsUpdateCount = 2,
                bioHeading = "Android developer",
                bioBody = "visual designer. diehard UI/UX nerd. mobile fiend. icons lover.",
                bioHashtags = listOf("#yourmomsfavorite", "#samplehastag"),
                links = listOf("gursky.studio", "link2", "link3", "link4")
            )
        )
        YourProfileDashboard(
            dashboardItem = ProfileDashboardItemDto(interactionCount = 1622)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(8) {
                YourHighlightItem(
                    highlightItem = ProfileHighlightItemDto(
                        title = "Highlight 2",
                        imageLink = "sad"
                    )
                )
            }
            item {
                YourHighlightItem(
                    highlightItem = ProfileHighlightItemDto(
                        title = "Highlight 2",
                        imageLink = "sad",
                        isAddNew = true
                    )
                )
            }
        }

        YourProfileTabs(hasExclusivePosts = true)
    }
}

@Composable
fun YourProfileTopBar(
    modifier: Modifier = Modifier,
    username: String
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = username,
                style = InstagramTypography.titleLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            //TODO -> overflow qoyanda eger username cox uzundusa down arrow icon gorunmur
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                modifier = Modifier
                    //TODO -> clickable burda olmalidi yoxsa adla birlikde rowun modifierinde
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_arrow_down_16),
                contentDescription = "Profile details???",
                tint = IconDefault
            )
        }

        Box {
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_threads_logo),
                contentDescription = "Threads on your profile",
                tint = IconDefault
            )
            NotificationBubbleWithCount(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 7.dp, y = (-5).dp),
                count = 1
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {

                    }
                ),
            imageVector = ImageVector.vectorResource(drawableR.ic_create),
            contentDescription = "Create new post",
            tint = IconDefault
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box {
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_menu),
                contentDescription = "Menu",
                tint = IconDefault
            )
            NotificationDotWithBorder(modifier = Modifier.align(Alignment.TopEnd))
        }
    }
}

@Composable
fun YourProfileInfo(
    modifier: Modifier = Modifier,
    profileInfoItem: ProfileInfoItemDto
) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            //TODO -> note buble custom design qalib
            AsyncImage(
                modifier = Modifier
                    .size(86.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(),
                        onClick = {

                        }
                    )
                    .border(
                        width = if (profileInfoItem.hasAlreadySeen) 1.dp else 3.dp,
                        brush = if (profileInfoItem.hasAlreadySeen) SolidColor(BorderSubtler)
                        else if (profileInfoItem.storyType == StoryType.CLOSE_FRIENDS) SolidColor(
                            BorderCloseFriends
                        )
                        else GradientStory,
                        shape = CircleShape
                    )
                    .padding(5.dp)
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

            CircleIconButton(
                modifier = Modifier
                    .offset(x = (-4).dp, y = (-4).dp)
                    .align(Alignment.BottomEnd)
                    .border(width = 3.dp, color = BorderDefaultInverted, shape = CircleShape)
                    .padding(3.dp),
                iconRes = drawableR.ic_add_16,
                backgroundColor = BackgroundInteractive,
                iconColor = IconDefaultInverted,
                iconSize = 16.dp,
                iconPadding = 1.dp,
                state = ButtonState.ENABLED,
                onClick = {

                }
            )
        }

        Row(
            modifier = Modifier
                .weight(1f)
//                .padding(horizontal = 32.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            //TODO -> bu hisse app-da nece clickable ona bax
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = profileInfoItem.postCount.toString(),
                    style = InstagramTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "posts",
                    style = InstagramTypography.bodyLarge
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = profileInfoItem.followerCount.toString(),
                    style = InstagramTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "followers",
                    style = InstagramTypography.bodyLarge
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = profileInfoItem.followingCount.toString(),
                    style = InstagramTypography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "following",
                    style = InstagramTypography.bodyLarge
                )
            }
        }
    }
}


@Composable
fun YourProfileBio(
    modifier: Modifier = Modifier,
    bioItem: ProfileBioItemDto
) {
    Column(
        modifier = Modifier
            .padding(bottom = 6.dp, start = 12.dp, end = 12.dp)
    ) {
        Row {
            Text(
                text = bioItem.fullName,
                style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                //TODO -> maxline restrict etmek lazimdimi deqiqlesdir
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = bioItem.pronoun.pronounFull,
                style = InstagramTypography.bodyLarge.copy(color = TextSubtle)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { }
                )
                .background(color = BackgroundSubtlerLight)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_threads_16),
                contentDescription = "Your thread updates"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${bioItem.username}  • ${bioItem.threadsUpdateCount} new",
                style = InstagramTypography.bodySmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            ThreadsNotificationDot()
        }

        Spacer(modifier = Modifier.height(6.dp))


        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = InstagramTypography.bodyLarge.copy(color = TextTag).toSpanStyle()
                ) {
                    append(bioItem.bioHeading + '\n')
                }
                withStyle(style = InstagramTypography.bodyLarge.toSpanStyle()) {
                    append(bioItem.bioBody)
                }
                bioItem.bioHashtags.forEach {
                    withStyle(
                        style = InstagramTypography.bodyLarge.copy(color = TextTag).toSpanStyle()
                    ) {
                        append("$it ")
                    }
                }
            },
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
            //TODO -> ellipsisden sonra more da qoy (comment ile eynidi deyesen)
        )

        //TODO -> bu hisse app-da nece clickable ona bax
        Row(
            modifier = Modifier
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(drawableR.ic_link_16),
                contentDescription = "Links"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${bioItem.links.first()} and ${bioItem.links.size - 1} more",
                style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

@Composable
fun YourProfileDashboard(
    modifier: Modifier = Modifier,
    dashboardItem: ProfileDashboardItemDto
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { }
                )
                .background(color = BackgroundSubtlerLight)
                .padding(10.dp)
        ) {
            Text(
                text = "Professional dashboard",
                style = InstagramTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = "${dashboardItem.interactionCount} accounts reached in the last 30 days.",
                style = InstagramTypography.bodySmall.copy(color = TextSubtle)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            DefaultButton(
                modifier = Modifier.weight(1f),
                state = ButtonState.ENABLED,
                textEnabled = "Edit profile",
                onClick = {

                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            DefaultButton(
                modifier = Modifier.weight(1f),
                state = ButtonState.ENABLED,
                textEnabled = "Share profile",
                onClick = {

                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            DefaultButton(
                modifier = Modifier.weight(1f),
                state = ButtonState.ENABLED,
                textEnabled = "Email",
                onClick = {

                }
            )
        }
    }
}

@Composable
fun YourHighlightItem(
    highlightItem: ProfileHighlightItemDto
) {
    Column(
        modifier = Modifier
            .padding(top = 4.dp, bottom = 12.dp)
            .width(56.dp)
    ) {
        if (highlightItem.isAddNew.not()) {  //TODO -> bu nece olmalidi best practice
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    )
                    .border(width = 3.dp, color = BorderHighlight, shape = CircleShape)
                    .padding(6.dp)
                    .clip(CircleShape),
                model = highlightItem.imageLink,
                placeholder = painterResource(drawableR.ic_highlight_slides),
                error = painterResource(drawableR.ic_launcher_background),
                contentDescription = "Highlight image",
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .border(width = 2.dp, color = BorderSubtler, shape = CircleShape)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = {

                        }
                    )
                    .padding(16.dp)
                    .size(24.dp),
                imageVector = ImageVector.vectorResource(drawableR.ic_plus),
                contentDescription = "Add new highlight"
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = if (highlightItem.isAddNew) "New" else highlightItem.title,
            style = InstagramTypography.bodySmall,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun YourProfileTabs(
    hasExclusivePosts: Boolean
) {
    Row {
        YourProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_grid_filled,
            iconNotSelected = drawableR.ic_grid_oultined,
            description = "Posts",
            isTabSelected = true
        )
        if (hasExclusivePosts)
            YourProfileTabItem(
                modifier = Modifier.weight(1f),
                iconSelected = drawableR.ic_exclusive_filled,
                iconNotSelected = drawableR.ic_exclusive,
                description = "Exclusives"
            )
        YourProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_reels_filled,
            iconNotSelected = drawableR.ic_reels_outlined,
            description = "Reels"
        )
        YourProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_filters,
            iconNotSelected = drawableR.ic_filters,
            description = "Filters"
        )
        YourProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_tag,
            iconNotSelected = drawableR.ic_tag,
            description = "Tagged posts"
        )
    }
}

@Composable
fun YourProfileTabItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconSelected: Int,
    @DrawableRes iconNotSelected: Int,
    isTabSelected: Boolean = false,
    description: String
) {
    //TODO -> burda dehset kod tekrari var best practice nedir
    if (isTabSelected) {
        Icon(
            modifier = modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {

                    }
                )
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2  //Position at the bottom
                    drawLine(
                        color = BorderDefault,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
                .padding(top = 8.dp, bottom = 7.dp),
            imageVector = ImageVector.vectorResource(iconSelected),
            contentDescription = description,
            tint = IconDefault
        )
    } else {
        Icon(
            modifier = modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {

                    }
                )
                .padding(top = 8.dp, bottom = 7.dp),
            imageVector = ImageVector.vectorResource(iconNotSelected),
            contentDescription = description,
            tint = IconSubtle
        )
    }
}

data class ProfileHighlightItemDto(
    val title: String,
    val imageLink: String,
    val isAddNew: Boolean = false
)

data class ProfileDashboardItemDto(
    val interactionCount: Int
)

data class ProfileBioItemDto(
    val username: String,
    val fullName: String,
    val pronoun: Pronoun,
    val threadsUpdateCount: Int,
    val bioHeading: String,
    val bioBody: String,
    val bioHashtags: List<String>,
    val links: List<String>
)

data class ProfileInfoItemDto(
    val profilePicture: String?,
    val storyType: StoryType,
    val hasAlreadySeen: Boolean = false,
    val postCount: Int,
    val followerCount: Int,
    val followingCount: Int
)

enum class StoryType {
    REGULAR,
    CLOSE_FRIENDS,
    LIVE,
    LIVE_WITH_OTHERS
}

enum class Pronoun(val pronounFull: String) {
    HE("he/him"),
    SHE("she/her"),
    THEY("they/them")
}


@Preview(showSystemUi = true)
@Composable
private fun YourProfileScreenPrev() {
    YourProfileScreen()
}