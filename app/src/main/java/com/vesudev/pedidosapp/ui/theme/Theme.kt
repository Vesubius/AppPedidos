package com.vesudev.pedidosapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF5A5A), // Un rojo más oscuro
    secondary = Color(0xFFEF9A9A), // Un rojo pastel más suave
    tertiary = Color(0xFFFF5252), // Un rojo coral más profundo
    onPrimary = Color(0xFFFFFFFF), // Usamos blanco para contrastar con el fondo oscuro
    background = Color(0xFF121212), // Fondo típico de modo oscuro
    surface = Color(0xFF1E1E1E), // Un tono más claro para la superficie
    onSecondary = Color(0xFFFFFFFF), // Blanco para contraste en elementos secundarios
    onBackground = Color(0xFFE0E0E0), // Gris claro para textos sobre el fondo oscuro
    onSurface = Color(0xFFE0E0E0) // Gris claro para textos sobre superficies oscuras
)


private val LightColorScheme = lightColorScheme(
    primary = RojoClaro,               // Color primario
    secondary = AzulClaro,             //color Secundario
    onPrimary = blanco,          // Color de los elementos sobre el color primario (como textos)
    background = grisOscuro,    // Fondo típico de modo claro (blanco)
    surface = grisClaro,       // Color de las superficies como tarjetas y diálogos (gris claro)
    onBackground = RojoOscuro,  // Texto y elementos sobre el fondo (negro para legibilidad)
    onSurface = Color(0xFF000000)      // Texto y elementos sobre las superficies (negro para buen contraste)



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PedidosAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
       /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        */
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}