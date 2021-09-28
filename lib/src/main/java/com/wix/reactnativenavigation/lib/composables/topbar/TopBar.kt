package com.wix.reactnativenavigation.lib.composables.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.wix.reactnativenavigation.lib.options.ButtonOptions
import com.wix.reactnativenavigation.lib.options.ComponentOptions

@Composable
fun TopBar(butons: List<ButtonOptions>, overFlowLimit: Int, title: String? = null) {
    TopAppBar() {
        Box(Modifier.fillMaxSize()) {
            ButtonsBar(
                Modifier
                    .fillMaxHeight()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.placeRelative(0, 0)
                        }
                    },
                btns = butons,
                overFlowLimit = overFlowLimit
            )

            //Title
            title?.let {
                Row(Modifier.layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(constraints.maxWidth, constraints.maxHeight) {
                        placeable.placeRelative(
                            constraints.maxWidth / 2 - placeable.width / 2, constraints
                                .maxHeight / 2 - placeable.height / 2
                        )
                    }
                }) {
                    Text(
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        text = title,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            ButtonsBar(
                Modifier
                    .fillMaxHeight()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.placeRelative(constraints.maxWidth - placeable.width, 0)
                        }
                    },
                btns = butons.takeLast(3),
                overFlowLimit = overFlowLimit
            )
        }
    }
}


@Preview(name = "MaButtonsBarOverFlowCollapseAll", showBackground = true)
@Composable
fun MaButtonsBarOverFlowCollapseAll() {
    TopBar(
        butons = listOf(
            ButtonOptions(icon = Icons.Default.ArrowBack),
            ButtonOptions(text = "Hello", color = Color.White),
            ButtonOptions(icon = Icons.Default.Share),
            ButtonOptions(component = ComponentOptions("Component1", "Id")),
            ButtonOptions(iconUri = "https://scontent.ftlv18-1.fna.fbcdn.net/v/t1.6435-9/74624080_1702255263241402_1720765285499142144_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=X4hGmVqhSokAX-iDKzW&_nc_ht=scontent.ftlv18-1.fna&oh=f91d189f22b6a9ecf1ecd7ff0aa59884&oe=6178BA4B")
        ),
        overFlowLimit = 2,
        title = "A very long title as it should ellipsize"
    )
}