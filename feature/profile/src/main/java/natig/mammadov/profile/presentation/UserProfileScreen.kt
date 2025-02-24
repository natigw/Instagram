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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import natig.mammadov.ui_toolkit.components.buttons.square.ActiveButton
import natig.mammadov.ui_toolkit.components.buttons.square.DefaultButton
import natig.mammadov.ui_toolkit.components.buttons.square.DefaultIconButton
import natig.mammadov.ui_toolkit.theme.BackgroundDefault
import natig.mammadov.ui_toolkit.theme.BackgroundSubtlerLight
import natig.mammadov.ui_toolkit.theme.BorderCloseFriends
import natig.mammadov.ui_toolkit.theme.BorderDefault
import natig.mammadov.ui_toolkit.theme.BorderDefaultInverted
import natig.mammadov.ui_toolkit.theme.BorderHighlight
import natig.mammadov.ui_toolkit.theme.BorderSubtler
import natig.mammadov.ui_toolkit.theme.GradientStory
import natig.mammadov.ui_toolkit.theme.IconDefault
import natig.mammadov.ui_toolkit.theme.IconSubtle
import natig.mammadov.ui_toolkit.theme.IconVerified
import natig.mammadov.ui_toolkit.theme.InstagramTypography
import natig.mammadov.ui_toolkit.theme.TextSubtle
import natig.mammadov.ui_toolkit.theme.TextTag
import natig.mammadov.ui_toolkit.R.drawable as drawableR


//TODO -> bu ekran userin oz profili ile demek olar eynidi ama bezi ferqler var, kod tezden yazilmalidi yoxsa ordakine deyisenlerle??

@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier.background(BackgroundDefault)
    ) {
        UserProfileTopBar("natig_w", true, true)
        UserProfileInfo(
            profileInfoItem = ProfileInfoItemDto(
                profilePicture = "https://picsum.photos/200/300",
                storyType = StoryType.REGULAR,
                hasAlreadySeen = false,
                postCount = 151,
                followerCount = 112,
                followingCount = 62
            )
        )
        UserProfileBio(
            bioItem = ProfileBioItemDto(
                username = "gursky.studio",
                fullName = "Natig Mammadov",
                pronoun = Pronoun.HE,
                threadsUpdateCount = 2,
                bioHeading = "Android developer",
                bioBody = "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecat... more",
                bioHashtags = listOf("#yourmomsfavorite", "#samplehastag"),
                links = listOf("gursky.studio", "link2", "link3", "link4")
            ),
            mutualUserProfilePicturesLikingPost = listOf("df", "dsfsds", "sdf"),
            mutualUsersLikingPost = listOf("your.friend", "another_friend"),
            mutualFollowerCount = 17
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(8) {
                UserHighlightItem(
                    highlightItem = ProfileHighlightItemDto(
                        title = "Highlight 2",
                        imageLink = "sad"
                    )
                )
            }
        }

        UserProfileTabs(hasExclusivePosts = true)
    }
}

@Composable
fun UserProfileTopBar(
    username: String,
    isUserVerified: Boolean,
    isNotificationsOn: Boolean
) {
    //TODO -> burani row kimi nece etmek olardi
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        //TODO -> bunlar icon olmalidi yoxsa button yoxsa iconButton yoxsa ne?
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {

                    }
                ),
            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_left),
            contentDescription = "Back",
            tint = IconDefault
        )
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                //TODO -> username cox uzun olsa nece etmek lazimdi (+verified badge)??
                .padding(horizontal = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = username,
                style = InstagramTypography.titleSmall.copy(fontWeight = FontWeight.Bold),
                //maxLines = 1,
                //overflow = TextOverflow.Ellipsis
            )
            if (isUserVerified) {
                Icon(
                    imageVector = ImageVector.vectorResource(drawableR.ic_verified_badge_small_16),
                    contentDescription = "Verified account",
                    tint = IconVerified
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(if (isNotificationsOn) drawableR.ic_notifications_on else drawableR.ic_notifications_off),
                contentDescription = "Notifications",
                tint = IconDefault
            )
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_options),
                contentDescription = "Options",
                tint = IconDefault
            )
        }
    }
}


//TODO -> ekranin sag-sol terefinde guideline kimi obshi columna padding vermek lazimdi yoxsa column icindeki viewlara ayri ayri?
@Composable
fun UserProfileInfo(
    modifier: Modifier = Modifier,
    profileInfoItem: ProfileInfoItemDto
) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
fun UserProfileBio(
    modifier: Modifier = Modifier,
    bioItem: ProfileBioItemDto,
    mutualUserProfilePicturesLikingPost: List<String>,
    mutualUsersLikingPost: List<String>,
    mutualFollowerCount: Int
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
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
                text = bioItem.username,
                style = InstagramTypography.bodySmall
            )
        }
        //TODO -> bunu deqiqlesdirki user profilde threads 2 new notification gorunur -> asagidaki kimi
//        Row(
//            modifier = Modifier
//                .clip(CircleShape)
//                .clickable(
//                    interactionSource = remember { MutableInteractionSource() },
//                    indication = rememberRipple(bounded = true),
//                    onClick = { }
//                )
//                .background(color = BackgroundSubtlerLight)
//                .padding(horizontal = 8.dp, vertical = 4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = ImageVector.vectorResource(drawableR.ic_threads_16),
//                contentDescription = "Your thread updates"
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            Text(
//                text = "${bioItem.username}  • ${bioItem.threadsUpdateCount} new",
//                style = InstagramTypography.bodySmall
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            Box(
//                modifier = Modifier
//                    .size(5.dp)
//                    .background(color = IconLink, shape = CircleShape)
//            )
//        }

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

        Spacer(modifier = Modifier.height(6.dp))

        //TODO -> bu hisse app-da nece clickable ona bax
        Row(
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

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .offset(x = (-2).dp)
                    .width(60.dp)
            ) {
                FollowedByMutualUser(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    imageLink = mutualUserProfilePicturesLikingPost[0]
                )
                FollowedByMutualUser(
                    modifier = Modifier.align(Alignment.Center),
                    imageLink = mutualUserProfilePicturesLikingPost[1]
                )
                FollowedByMutualUser(
                    modifier = Modifier.align(Alignment.CenterStart),
                    imageLink = mutualUserProfilePicturesLikingPost[2]
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = buildAnnotatedString {
                    withStyle(style = InstagramTypography.bodyLarge.toSpanStyle()) {
                        append("Followed by ")
                    }
                    withStyle(
                        style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                            .toSpanStyle()
                    ) {
                        append(mutualUsersLikingPost[0])
                    }
                    //TODO -> index check eleki listde index null-dirsa crash atmasin
                    withStyle(
                        style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                            .toSpanStyle()
                    ) {
                        append(", ${mutualUsersLikingPost[1]}")
                    }
                    withStyle(style = InstagramTypography.bodyLarge.toSpanStyle()) {
                        append(" and ")
                    }
                    withStyle(
                        style = InstagramTypography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                            .toSpanStyle()
                    ) {
                        append("${mutualFollowerCount - 2} others")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            var state by remember { mutableStateOf(ButtonState.ENABLED) }
            ActiveButton(
                modifier = Modifier.weight(1f),
                state = state,
                textEnabled = "Follow",
                textCompleted = "Following",
                addChevronOnCompleted = true,
                onClick = {
                    //request to api to follow and state->loading->completed
                    state = ButtonState.COMPLETED
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            DefaultButton(
                modifier = Modifier.weight(1f),
                state = ButtonState.ENABLED,
                textEnabled = "Message",
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
            Spacer(modifier = Modifier.width(4.dp))
            //TODO -> bu button ne vaxtsa mavi ve ya filled icon olur deqiqlesdir
            DefaultIconButton(
             iconRes = drawableR.ic_suggested_users_outlined_16,
                state = ButtonState.ENABLED,
                onClick = {

                }
            )
        }
    }
}

@Composable
fun FollowedByMutualUser(
    modifier: Modifier,
    imageLink: String
) {
    AsyncImage(
        modifier = modifier
            .size(28.dp)
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
        model = imageLink,
        placeholder = painterResource(drawableR.ic_highlight_slides),
        error = painterResource(drawableR.ic_launcher_background),
        contentDescription = "Users liking this post",
        contentScale = ContentScale.Crop
    )
}


@Composable
fun UserHighlightItem(
    highlightItem: ProfileHighlightItemDto
) {
    Column(
        modifier = Modifier
            .padding(top = 4.dp, bottom = 12.dp)
            .width(56.dp)
    ) {
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
fun UserProfileTabs(
    hasExclusivePosts: Boolean
) {
    Row {
        UserProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_grid_filled,
            iconNotSelected = drawableR.ic_grid_oultined,
            description = "Posts",
            isTabSelected = true
        )
        if (hasExclusivePosts)
            UserProfileTabItem(
                modifier = Modifier.weight(1f),
                iconSelected = drawableR.ic_exclusive_filled,
                iconNotSelected = drawableR.ic_exclusive,
                description = "Exclusives"
            )
        UserProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_reels_filled,
            iconNotSelected = drawableR.ic_reels_outlined,
            description = "Reels"
        )
        UserProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_filters,
            iconNotSelected = drawableR.ic_filters,
            description = "Filters"
        )
        UserProfileTabItem(
            modifier = Modifier.weight(1f),
            iconSelected = drawableR.ic_tag,
            iconNotSelected = drawableR.ic_tag,
            description = "Tagged posts"
        )
    }
}

@Composable
fun UserProfileTabItem(
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

@Preview(showSystemUi = true)
@Composable
private fun UserProfileScreenPrev() {
    UserProfileScreen()
}