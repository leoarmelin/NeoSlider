package com.leoarmelin.customslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoarmelin.customslider.examples.ExampleFour
import com.leoarmelin.customslider.examples.ExampleOne
import com.leoarmelin.customslider.examples.ExampleThree
import com.leoarmelin.customslider.examples.ExampleTwo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0D9996)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    24.dp,
                    Alignment.CenterVertically
                )
            ) {
                ExampleOne()
                ExampleTwo()
                ExampleThree()
                ExampleFour()
            }
        }
    }
}

@Preview
@Composable
fun CustomSliderPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D9996)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            24.dp,
            Alignment.CenterVertically
        )
    ) {
        ExampleOne()
        ExampleTwo()
        ExampleThree()
        ExampleFour()
    }
}