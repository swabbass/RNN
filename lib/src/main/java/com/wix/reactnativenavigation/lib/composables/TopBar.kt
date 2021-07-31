package com.wix.reactnativenavigation.lib.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayout(modifier: Modifier, content: @Composable () -> Unit) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            val p = measurable.measure(constraints)
            p
        }
        var xPos = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = xPos, y = 0)
                xPos += placeable.measuredWidth
            }
        }
    }

}

@Preview(name = "Preview")
@Composable
fun customLayoutPreview() {
    CustomLayout(Modifier.requiredSize(1080.dp, 100.dp)) {
        Box(
            Modifier
                .size(32.dp, 64.dp)
                .background(Color.Red)
        ) {

        }
        Box(
            Modifier
                .size(100.dp, 64.dp)
                .background(Color.Green)
        ) {

        }
        Box(
            Modifier
                .size(32.dp, 64.dp)
                .background(Color.Yellow)
        ) {

        }
    }
}

@Composable
fun ButtonsBar(btns: List<ImageVector>, collapseAll: Boolean = false) {
    Row() {
        if(btns.size>2){
            if(collapseAll){
                OverflowMenu {
                    btns.forEach {
                        IconButton(
                            onClick = { },
                            enabled = true,
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = "desc",
                            )
                        }
                    }
                }
            }else{
                btns.forEachIndexed { index, imageVector ->
                    if (index < 2) {
                        IconButton(
                            onClick = { },
                            enabled = true,
                        ) {
                            Icon(
                                imageVector = imageVector,
                                contentDescription = "desc",
                            )
                        }
                    }else return@forEachIndexed
                }
                OverflowMenu {
                    btns.forEachIndexed { index, imageVector ->
                        if(index>=2){
                            IconButton(
                                onClick = { },
                                enabled = true,
                            ) {
                                Icon(
                                    imageVector = imageVector,
                                    contentDescription = "desc",
                                )
                            }
                        }
                    }
                }

            }
        }else{
            btns.forEach {
                IconButton(
                    onClick = { },
                    enabled = true,
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = "desc",
                    )
                }
            }
        }

    }
}

@Composable
fun OverflowMenu(content: @Composable () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = {
        showMenu = !showMenu
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "More",
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        content()
    }
}

@Preview(name = "ButtonsBarNormal", showBackground = true)
@Composable
fun ButtonsBarPreviewNormal() {
    ButtonsBar(
        btns = listOf(
            Icons.Default.ArrowBack,
            Icons.Default.Share
        )
    )
}

@Preview(name = "ButtonsBarOverFlow", showBackground = true)
@Composable
fun ButtonsBarPreviewOverFlow() {
    ButtonsBar(
        btns = listOf(
            Icons.Default.ArrowBack,
            Icons.Default.Share,
            Icons.Default.Done,
            Icons.Default.Settings
        )
    )
}

@Preview(name = "ButtonsBarOverFlowColapseAll", showBackground = true)
@Composable
fun ButtonsBarPreviewOverFlowCollapseAll() {
    ButtonsBar(
        collapseAll = true,
        btns = listOf(
            Icons.Default.ArrowBack,
            Icons.Default.Share,
            Icons.Default.Done
        )
    )
}


@Preview(name = "CustomToolbar")
@Composable
fun CustomToolbarPreview() {
    val appBarHorizontalPadding = 4.dp
    val titleIconModifier = Modifier
        .fillMaxHeight()
        .width(72.dp - appBarHorizontalPadding)
    TopAppBar(){
        Box(Modifier.fillMaxSize()) {

            //Navigation Icon
            Row(
                Modifier
                    .fillMaxHeight()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.placeRelative(0, 0)
                        }
                    },verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { },
                    enabled = true,
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
                OverflowMenu{
                    IconButton(
                        onClick = { },
                        enabled = true,
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            }

            //Title
            Row(Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(constraints.maxWidth,constraints.maxHeight){
                    placeable.placeRelative(constraints.maxWidth/2 - placeable.width/2,constraints
                        .maxHeight/2 - placeable.height/2)
                }
            }) {
                Text(
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = "Helloefefefefefefefe",
                    overflow = TextOverflow.Ellipsis
                )
            }

            //actions
            Row(
                Modifier
                    .fillMaxHeight()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.placeRelative(constraints.maxWidth - placeable.width, 0)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Button(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Share, "Share")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Share, "Share")
                    }
                }
            )
        }
    }
//   Row(Modifier.size(1080.dp,64.dp)) {
//       Box(Modifier.size(32.dp,64.dp).background(Color.Cyan)) {
//
//       }
//   }
}