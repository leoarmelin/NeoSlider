package com.leoarmelin.neo_slider

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@SuppressLint("ModifierParameter")
@Composable
fun CustomSlider(
    value: Float,
    onSlide: (newValue: Float, percentage: Float) -> Unit,
    onDragStart: ((offset: Offset) -> Unit)? = null,
    onDragEnd: (() -> Unit)? = null,
    containerModifier: Modifier? = null,
    trackModifier: Modifier? = null,
    thumbModifier: Modifier? = null,
    trackComposable: (@Composable () -> Unit)? = null,
    thumbComposable: (@Composable () -> Unit)? = null,
    hideThumb: Boolean = false,
    onUpdateContainerWidth: ((width: Float) -> Unit)? = null,
) {
    val density = LocalDensity.current.density
    var containerWidth by remember { mutableStateOf(0f) }
    var thumbWidth by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .then(
                containerModifier ?: Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0x40000000))
            )
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset ->
                        onSlide(offset.x, offset.x / containerWidth)
                        if (onDragStart != null) onDragStart(offset)
                    },
                    onHorizontalDrag = { change, _ ->
                        val offsetX = when (change.position.x) {
                            in Float.NEGATIVE_INFINITY..0f -> 0f
                            in containerWidth..Float.POSITIVE_INFINITY -> containerWidth
                            else -> change.position.x
                        }
                        onSlide(offsetX, offsetX / containerWidth)
                    },
                    onDragEnd = onDragEnd ?: {}
                )
            }
            .onGloballyPositioned { layoutCoordinates ->
                containerWidth = layoutCoordinates.size.width.toFloat()
                if (onUpdateContainerWidth != null) onUpdateContainerWidth(containerWidth)
            }
    ) {
        Box(
            modifier = Modifier
                .then(
                    trackModifier ?: if (trackComposable == null) Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(Color.Blue, RoundedCornerShape(5.dp)) else Modifier
                )
                .align(Alignment.CenterStart)
        ) {
            if (trackComposable != null) trackComposable()
        }

        if (!hideThumb) {
            Box(
                modifier = Modifier
                    .offset(x = ((value - (thumbWidth / 2)) / density).dp)
                    .then(
                        thumbModifier ?: if (thumbComposable == null) Modifier
                            .size(20.dp)
                            .background(Color.Red, CircleShape) else Modifier
                    )
                    .align(Alignment.CenterStart)
                    .onGloballyPositioned { layoutCoordinates ->
                        thumbWidth = layoutCoordinates.size.width.toFloat()
                    },
            ) {
                if (thumbComposable != null) thumbComposable()
            }
        }
    }
}