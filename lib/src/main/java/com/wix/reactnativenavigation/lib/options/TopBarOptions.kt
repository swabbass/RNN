package com.wix.reactnativenavigation.lib.options

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class TextOptions(val content: String, val color: @RawValue Color) : Parcelable

@Parcelize
data class TopBarOptions(
    val title: TextOptions? = null,
    val rightButtonOptions: ButtonsBarOptions? = null,
    val leftButtonOptions: ButtonsBarOptions? = null
) : Parcelable
