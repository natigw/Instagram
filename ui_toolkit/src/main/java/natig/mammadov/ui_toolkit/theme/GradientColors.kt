package natig.mammadov.ui_toolkit.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

// GRADIENT TOKENS

//General
val GradientStory = Brush.linearGradient(
    colorStops = arrayOf(
        0.0f to Purple80,
        0.17f to Magenta100,
        0.36f to Pink100,
        0.63f to Orange100,
        0.83f to Yellow100
    ),
    start = Offset(Float.POSITIVE_INFINITY, 0f),  //top-right
    end = Offset(0f, Float.POSITIVE_INFINITY)     //bottom-left
)


val GradientText = Brush.linearGradient(
    colorStops = arrayOf(
        0.0f to Purple80,
        0.17f to Magenta100,
        0.48f to Pink100,
        0.69f to Orange100
    ),
    start = Offset(Float.POSITIVE_INFINITY, 0f),  //top-right
    end = Offset(0f, Float.POSITIVE_INFINITY)     //bottom-left
)


private const val gradientLiveAlpha = 0.4f
val GradientLive = Brush.radialGradient(
    colorStops = arrayOf(
        0.84f to Transparent,
        1.0f to White100.copy(alpha = gradientLiveAlpha)
    )
)


private const val gradientLiveSubtleAlpha = 0.3f
val GradientLiveSubtle = Brush.radialGradient(
    colorStops = arrayOf(
        0.67f to Transparent,
        1.0f to White100.copy(alpha = gradientLiveSubtleAlpha)
    )
)


private const val gradientScrimAlpha = 0.35f
val GradientScrim = Brush.verticalGradient(
    colorStops = arrayOf(
        0.0f to Black100.copy(alpha = gradientScrimAlpha),
        0.5f to Black40.copy(alpha = gradientScrimAlpha),
        1f to Transparent,
    )
)


// Stickers
val StickerGradient = Brush.linearGradient(
    colorStops = arrayOf(
        0.0f to StickerPurple,
        0.17f to StickerMagenta,
        0.36f to StickerPink,
        0.63f to StickerOrange,
        0.83f to StickerYellow
    ),
    start = Offset.Infinite,  //bottom-right
    end = Offset.Zero     //top-left
)

val StickerRainbowIcon = Brush.horizontalGradient(
    colorStops = arrayOf(
        0.0f to StickerRed,
        1f to StickerOrange
    )
)

val StickerRainbowText = Brush.horizontalGradient(
    colorStops = arrayOf(
        0.0f to StickerRed,
        0.13f to StickerOrange,
        0.33f to StickerYellow,
        0.60f to StickerGreen,
        0.80f to StickerBlue,
        1f to StickerPurple,
    )
)