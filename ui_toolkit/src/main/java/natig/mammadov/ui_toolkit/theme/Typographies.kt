package natig.mammadov.ui_toolkit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val InstagramTypography = Typography(
    titleLarge = TextStyle( //title1
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,  //for Bold -> InstagramTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
        fontSize = 21.sp,       // 21px font size
        lineHeight = 0.sp,      // Auto line height (Compose will calculate)
        letterSpacing = 0.21.sp // 1% of 21px (21 * 0.01 = 0.21sp)
    ),
    titleMedium = TextStyle( //title2
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    titleSmall = TextStyle( //title3
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.14.sp
    ),
    headlineMedium = TextStyle( //headline
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,  //Semibold
        fontSize = 13.sp,
        letterSpacing = 0.13.sp
    ),
    bodyLarge = TextStyle( //body
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = 0.26.sp    //0.13.sp for Bold
    ),
    bodyMedium = TextStyle( //callout
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.24.sp
    ),
    bodySmall = TextStyle( //footnote
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,  //Semibold
        fontSize = 11.sp,
        letterSpacing = 0.33.sp    //0.22.sp for Semibold
    ),
    labelMedium = TextStyle( //caption1
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,  //Semibold
        fontSize = 10.sp,
        letterSpacing = 0.4.sp
    ),
    labelSmall = TextStyle( //caption2
        fontFamily = SFProFontFamily,
        fontWeight = FontWeight.Normal,  //Semibold
        fontSize = 7.sp,
        letterSpacing = 0.42.sp
    )
)