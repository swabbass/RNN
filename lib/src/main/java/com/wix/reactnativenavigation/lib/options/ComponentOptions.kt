package com.wix.reactnativenavigation.lib.options

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComponentOptions(val name: String, val componentId: String):Parcelable
