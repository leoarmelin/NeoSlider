package com.leoarmelin.customslider.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.leoarmelin.neo_slider.CustomSlider
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun ExampleTwo() {
    val density = LocalDensity.current.density
    var sliderState by remember { mutableStateOf(100f) }
    var sliderPercentage by remember { mutableStateOf(1f) }
    var containerWidth by remember { mutableStateOf(0f) }

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
            .gradientBackground(listOf(Color(0xFFCBAACB), Color(0xFFFFC8A2)), 45f),
        thumbModifier = Modifier
            .size(32.dp)
            .background(Color.White, CircleShape),
        hideThumb = true,
        onUpdateContainerWidth = onUpdateContainerWidth
    )
}

private fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {

        // Setting the angle in radians
        val angleRad = angle / 180f * PI

        // Fractional x
        val x = kotlin.math.cos(angleRad).toFloat()

        // Fractional y
        val y = kotlin.math.sin(angleRad).toFloat()

        // Set the Radius and offset as shown below
        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        // Setting the exact offset
        val exactOffset = Offset(
            x = kotlin.math.min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - kotlin.math.min(offset.y.coerceAtLeast(0f), size.height)
        )

        // Draw a rectangle with the above values
        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)