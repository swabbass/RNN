package com.wix.reactnativenavigation.lib.options

import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonOptions(
    val id: String = "btn" + View.generateViewId(),
    val text: String? = null,
    val allCaps: Boolean? = null,
    val color: Color? = null,
    val fontSize: Number? = null,
    val iconUri: String? = null,
    val icon:ImageVector? =null,
    val component: ComponentOptions? = null
) {
}