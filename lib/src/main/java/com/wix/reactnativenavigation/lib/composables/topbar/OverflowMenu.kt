package com.wix.reactnativenavigation.lib.composables.topbar

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.wix.reactnativenavigation.lib.options.ButtonOptions

@Composable
fun OverflowMenu(menuItems: List<ButtonOptions>) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    val onClick: (ButtonOptions) -> Unit = { clickedButton ->
        showMenu = false
        Toast.makeText(context, "Click ${clickedButton.id}", Toast.LENGTH_SHORT).show()
    }
    IconButton(onClick = {
        showMenu = !showMenu
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "More",
        )
    }
    Box() {
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            menuItems.forEach { buttonOptions ->
                DropdownMenuItem(onClick = { onClick(buttonOptions) }) {
                    ButtonComposable(buttonOptions, onClick)
                }
            }
        }
    }

}