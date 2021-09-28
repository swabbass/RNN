package com.wix.reactnativenavigation.lib.composables.topbar

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.wix.reactnativenavigation.lib.options.ButtonOptions

@Composable
fun ButtonsBar(
    modifier: Modifier = Modifier, btns: List<ButtonOptions>, collapseAll: Boolean = false,
    overFlowLimit: Int = 2
) {
    val context = LocalContext.current
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (collapseAll) {
            OverflowMenu(btns)
        } else {
            btns.take(overFlowLimit).forEach { buttonOptions ->
                ButtonComposable(buttonOptions) {
                    Toast.makeText(context, "Click ${buttonOptions.id}", Toast.LENGTH_SHORT).show()
                }
            }
            if (btns.size > overFlowLimit) {
                OverflowMenu(btns.takeLast(btns.size - overFlowLimit))
            }
        }
    }
}