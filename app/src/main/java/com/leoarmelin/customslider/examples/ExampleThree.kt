package com.leoarmelin.customslider.examples

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.leoarmelin.neo_slider.CustomSlider

@Composable
fun ExampleThree() {
    val density = LocalDensity.current.density
    var sliderState by remember { mutableStateOf(0f) }
    var sliderPercentage by remember { mutableStateOf(0f) }
    var containerWidth by remember { mutableStateOf(0f) }
    val backgroundAnimation by animateColorAsState(when (sliderPercentage) {
        in 0f..0.33f -> Color(0xFFFF968A)
        in 0.33f..0.66f -> Color(0xFFFFFFB5)
        in 0.66f..1f -> Color(0xFFBFFCC6)
        else -> Color.White
    })

    val onUpdateContainerWidth: (newWidth: Float) -> Unit = { containerWidth = it }

    CustomSlider(
        value = sliderState,
        onSlide = { newValue, percentage ->
            sliderState = newValue
            sliderPercentage = percentage
        },
        containerModifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .height(30.dp),
        trackModifier = Modifier
            .width((containerWidth * sliderPercentage / density).dp)
            .height(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundAnimation, RoundedCornerShape(8.dp)),
        thumbModifier = Modifier
            .size(32.dp)
            .background(backgroundAnimation, CircleShape),
        onUpdateContainerWidth = onUpdateContainerWidth
    )
}