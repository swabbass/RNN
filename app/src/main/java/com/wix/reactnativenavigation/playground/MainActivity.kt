package com.wix.reactnativenavigation.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wix.reactnativenavigation.lib.composables.CustomLayout
import com.wix.reactnativenavigation.playground.ui.theme.RNNTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RNNTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   CustomLayout(modifier = Modifier.fillMaxWidth()) {
                       Box(Modifier.size(10.dp,64.dp).background(Color.Red)) {

                       }
                       Box(Modifier.size(100.dp,64.dp).background(Color.Green)) {

                       }
                       Box(Modifier.size(32.dp,64.dp).background(Color.Yellow)) {

                       }
                   }

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RNNTheme {
        CustomLayout(modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.size(10.dp,64.dp).background(Color.Red)) {

            }
            Box(Modifier.size(100.dp,64.dp).background(Color.Green)) {

            }
            Box(Modifier.size(32.dp,64.dp).background(Color.Yellow)) {

            }
        }
    }
}