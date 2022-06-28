package com.leoarmelin.customslider.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leoarmelin.neo_slider.CustomSlider

@Composable
fun ExampleOne() {
    var sliderState by remember { mutableStateOf(0f) }

    CustomSlider(
        value = sliderState,
        onSlide = { newValue, percentage ->
            sliderState = newValue
        },
        containerModifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .height(30.dp),
        trackModifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Color(0xFFCCE2CB), RoundedCornerShape(4.dp)),
        thumbModifier = Modifier
            .size(32.dp)
            .background(Color(0xFF97C1A9), CircleShape),
    )
}