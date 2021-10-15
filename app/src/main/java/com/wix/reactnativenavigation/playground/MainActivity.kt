package com.wix.reactnativenavigation.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.wix.reactnativenavigation.lib.composables.topbar.MaButtonsBarOverFlowCollapseAll
import com.wix.reactnativenavigation.playground.ui.theme.RNNTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RNNTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   Column() {
                       MaButtonsBarOverFlowCollapseAll()
                   }

                }
            }
        }
    }
}
