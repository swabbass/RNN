package com.wix.reactnativenavigation.lib.composables.topbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.wix.reactnativenavigation.lib.options.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private const val TAG = "TopBar"
var count = 0;
@Composable
fun TopBar(topBarOptions: TopBarOptions) {
    var leftWidth by remember { mutableStateOf(0) }
    var rightWidth by remember { mutableStateOf(0) }
    var titleSize by remember { mutableStateOf(0) }
    TopAppBar() {
        Box(Modifier.fillMaxSize()) {

            Log.d(TAG, "recomposeCount: ${++count}")

            topBarOptions.leftButtonOptions?.let {
                ButtonsBar(
                    Modifier
                        .fillMaxHeight()
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            leftWidth = placeable.width
                            Log.d(TAG, "TopBarLeftButtons: left $leftWidth")

                            layout(constraints.maxWidth, constraints.maxHeight) {
                                placeable.placeRelative(0, 0)
                            }
                        },
                    btns = it.buttons,
                    overFlowLimit = it.overFlowSize
                )
            }
            //Title
            topBarOptions.title?.let {
                Row(Modifier.wrapContentWidth().layout { measurable, constraints ->

                    val barWidth = constraints.maxWidth
                    val barCenter = barWidth / 2
                    val titleWidth = if(titleSize!=0) min(titleSize,barWidth - rightWidth - leftWidth) else barWidth - rightWidth - leftWidth
                    val titleCenter = titleWidth / 2
                    val leftBarEnd = leftWidth
                    val rightBarStart = barWidth - rightWidth
                    val initialTitleLeft = barCenter - titleCenter
                    val initialTitleRight = barCenter + titleCenter
                    val pivotLeft = max(leftBarEnd-initialTitleLeft,0)
                    val pivotedTitleRight = initialTitleRight+pivotLeft
                    val pivotRight = if(pivotedTitleRight > rightBarStart) pivotedTitleRight-rightBarStart else 0
                    Log.d(TAG, "barWidth:$barWidth, titleWidth:$titleWidth, leftBarEnd:$leftBarEnd, " +
                            "rightBarStart:$rightBarStart, initialTitleLeft:$initialTitleLeft, " +
                            "initialTitleRight:$initialTitleRight, pivotLeft:$pivotLeft, " +
                            "pivotedTitleRight:$pivotedTitleRight, pivotRight:$pivotRight ")

                    val placeable = measurable.measure(constraints.copy(maxWidth = titleWidth))

                    layout(constraints.maxWidth, constraints.maxHeight) {

                        placeable.placeRelative(
                             initialTitleLeft + pivotLeft - pivotRight, constraints
                                .maxHeight / 2 - placeable.height / 2
                        )
                    }
                }) {
                    Text(
                        modifier=Modifier.onGloballyPositioned {
                            titleSize=it.size.width
                        }.background(color=Color.Blue),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        text = it.content,
                        overflow = TextOverflow.Ellipsis,

                    )
                }
            }
            topBarOptions.rightButtonOptions?.let {
                ButtonsBar(
                    Modifier
                        .fillMaxHeight()
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            rightWidth = placeable.width
                            Log.d(TAG, "TopBarRightButtons: right $rightWidth")
                            layout(constraints.maxWidth, constraints.maxHeight) {
                                placeable.placeRelative(constraints.maxWidth - placeable.width, 0)
                            }
                        },
                    btns = it.buttons.takeLast(3),
                    overFlowLimit = it.overFlowSize
                )
            }

        }
    }
}


@Preview(name = "MaButtonsBarOverFlowCollapseAll", showBackground = true)
@Composable
fun MaButtonsBarOverFlowCollapseAll() {

    val leftButtons = mutableListOf(
        ButtonOptions(icon = Icons.Default.ArrowBack),
        ButtonOptions(icon = Icons.Default.Share),
//        ButtonOptions(icon = Icons.Default.Home),
    )
    var topBarOptions by remember {
        mutableStateOf(
            TopBarOptions(
                title = TextOptions("I'm Title!", Color.White),
                rightButtonOptions = ButtonsBarOptions(
                    2, listOf(
//                        ButtonOptions(text = "Hello", color = Color.White),
//                        ButtonOptions(icon = Icons.Default.Share),
//                        ButtonOptions(component = ComponentOptions("Component1", "Id")),
                        ButtonOptions(iconUri = "https://scontent.ftlv18-1.fna.fbcdn.net/v/t1.6435-9/74624080_1702255263241402_1720765285499142144_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=X4hGmVqhSokAX-iDKzW&_nc_ht=scontent.ftlv18-1.fna&oh=f91d189f22b6a9ecf1ecd7ff0aa59884&oe=6178BA4B")
                    )
                ),
                leftButtonOptions = ButtonsBarOptions(
                    2, leftButtons
                )
            )
        )
    }
    Column() {
        TopBar(
            topBarOptions
        )
        TextButton(onClick = {
            topBarOptions = topBarOptions.copy(
                leftButtonOptions =
                topBarOptions.leftButtonOptions?.copy(buttons = leftButtons.also {
                    it.add(
                        ButtonOptions(
                            icon = Icons
                                .Default
                                .Share
                        )
                    )
                })
            )
        }) {
            Text(text = "Add Left Button")
        }

        TextButton(onClick = {
            topBarOptions = topBarOptions.copy(
                title = topBarOptions.title?.copy(content =
                "A Verry Long Long Long Long Long Long Title Title Tile")
            )
        }) {
            Text(text = "Long")
        }

        TextButton(onClick = {
            topBarOptions = topBarOptions.copy(
                title = topBarOptions.title?.copy(content =
                "I'm Changed Title!")
            )
        }) {
            Text(text = "Short")
        }
    }

}