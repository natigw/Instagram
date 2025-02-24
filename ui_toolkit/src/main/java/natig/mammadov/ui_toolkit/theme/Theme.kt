package natig.mammadov.ui_toolkit.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext


val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }

private val LightColorScheme = lightColorScheme(
    primary = BackgroundDefaultInverted,
    onPrimary = TextDefaultInverted,
)

private val DarkColorScheme = darkColorScheme(
    primary = BackgroundInteractive,
    onPrimary = TextDefault,
//    primaryContainer = ,
//    onPrimaryContainer = onPrimaryContainer,
//    inversePrimary = inversePrimary,
//    secondary = secondary,
//    onSecondary = onSecondary,
//    secondaryContainer = secondaryContainer,
//    onSecondaryContainer = onSecondaryContainer,
//    tertiary = tertiary,
//    onTertiary = onTertiary,
//    tertiaryContainer = tertiaryContainer,
//    onTertiaryContainer = onTertiaryContainer,
//    background = background,
//    onBackground = onBackground,
//    surface = surface,
//    onSurface = onSurface,
//    surfaceVariant = surfaceVariant,
//    onSurfaceVariant = onSurfaceVariant,
//    surfaceTint = surfaceTint,
//    inverseSurface = inverseSurface,
//    inverseOnSurface = inverseOnSurface,
//    error = error,
//    onError = onError,
//    errorContainer = errorContainer,
//    onErrorContainer = onErrorContainer,
//    outline = outline,
//    outlineVariant = outlineVariant,
//    scrim = scrim,
//    surfaceBright = surfaceBright,
//    surfaceContainer = surfaceContainer,
//    surfaceContainerHigh = surfaceContainerHigh,
//    surfaceContainerHighest = surfaceContainerHighest,
//    surfaceContainerLow = surfaceContainerLow,
//    surfaceContainerLowest = surfaceContainerLowest,
//    surfaceDim = surfaceDim
)


@Composable
fun InstagramTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,  //Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme)
                dynamicDarkColorScheme(context)
            else
                dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme
    ) {
        //To keep ripple and other configs
        MaterialTheme(
            typography = InstagramTypography,
            colorScheme = colorScheme,
            content = content
        )
    }
}


//TODO -> bu yaxsi usuldu??
object InstagramTheme {
    val color
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
//    val typography
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalTypo
}