package com.samar.busbooking.presentation.ui.theme

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.samar.busbooking.R

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

// GoogleFont.Provider initialization ...

@OptIn(ExperimentalTextApi::class)
val pacificoFontName = GoogleFont("Pacifico")

@OptIn(ExperimentalTextApi::class)
val pacifico = FontFamily(Font(googleFont = pacificoFontName, fontProvider = provider))

@OptIn(ExperimentalTextApi::class)
val kanitFontName = GoogleFont("Kanit")

@OptIn(ExperimentalTextApi::class)
val kanitFontFamily = FontFamily(
    Font(googleFont = kanitFontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = kanitFontName, fontProvider = provider, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
)

@OptIn(ExperimentalTextApi::class)
val poppinsFontName = GoogleFont("Poppins")

@OptIn(ExperimentalTextApi::class)
val poppinsFontFamily = FontFamily(
    Font(googleFont = poppinsFontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = poppinsFontName, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = poppinsFontName, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = poppinsFontName, fontProvider = provider, weight = FontWeight.Thin),
    Font(googleFont = poppinsFontName, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = poppinsFontName, fontProvider = provider, weight = FontWeight.ExtraLight),
    Font(googleFont = poppinsFontName, fontProvider = provider, weight = FontWeight.ExtraBold),
)