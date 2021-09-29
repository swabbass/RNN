package com.wix.reactnativenavigation.lib.options

import android.os.Parcelable
import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ButtonOptions(
    val id: String = "btn" + View.generateViewId(),
    val text: String? = null,
    val allCaps: Boolean? = null,
    val color: @RawValue Color? = null,
    val fontSize: Number? = null,
    val iconUri: String? = null,
    val icon: @RawValue ImageVector? = null,
    val component: ComponentOptions? = null
) : Parcelable

@Parcelize
data class ButtonsBarOptions(
    val overFlowSize: Int,
    val buttons: List<ButtonOptions>
) : Parcelable