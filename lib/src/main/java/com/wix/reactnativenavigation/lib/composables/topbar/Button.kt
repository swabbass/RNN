package com.wix.reactnativenavigation.lib.composables.topbar

import android.graphics.Color
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.wix.reactnativenavigation.lib.options.ButtonOptions

@Composable
fun ButtonComposable(buttonOptions: ButtonOptions, onClick: (ButtonOptions) -> Unit) {
    when {
        buttonOptions.component != null -> {
            AndroidView(factory = { context ->
                FrameLayout(context).apply {
                    setBackgroundColor(Color.CYAN)
                    addView(
                        TextView(context).apply {
                            text = buttonOptions.component.name
                        },
                        FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.WRAP_CONTENT,
                            FrameLayout.LayoutParams.WRAP_CONTENT
                        )
                    )
                }
            })
        }
        buttonOptions.text != null -> {
            TextButton(onClick = { onClick(buttonOptions) }) {
                Text(
                    buttonOptions.text,
                    textAlign = TextAlign.Center,
                    color = buttonOptions.color ?: androidx.compose.ui.graphics.Color.Unspecified
                )
            }
        }
        buttonOptions.iconUri != null -> {
            IconButton(onClick = { onClick(buttonOptions) }) {
                Image(
                    painter = rememberImagePainter(buttonOptions.iconUri),
                    contentDescription = null,
                )
            }
        }
        buttonOptions.icon != null -> {
            IconButton(onClick = { onClick(buttonOptions) }) {
                Icon(
                    imageVector = buttonOptions.icon,
                    tint = buttonOptions.color ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                    contentDescription = "desc",
                )
            }
        }
    }
}