package presentation.compose.component.interaction

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import util.rememberHapticFeedback
import kotlin.math.roundToInt


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeInteraction(
    sheetOffset: Float,
    onSheetOffsetChanged: (Float) -> Unit,
    onDragDone: () -> Unit = {},
    onActiveLongClick: () -> Unit = {},
    onActiveDoubleClick: () -> Unit = {},
    onDoneLongClick: () -> Unit = {},
    snapThreshold: Float = 0.4f,
    enableHapticFeedback: Boolean = true,
    content: @Composable () -> Unit,
    sheetContent: @Composable () -> Unit = { getDefaultSheetContent() },
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val hapticFeedback = rememberHapticFeedback()

    var contentWidth by remember { mutableStateOf(0f) }
    var contentHeight by remember { mutableStateOf(0.dp) }

    val currentSheetOffset = remember { Animatable(0f) }
    val isSheetFullyDragged = -currentSheetOffset.value == contentWidth && contentWidth != 0f
    var dragEnabled by remember { mutableStateOf(true) }

    var hapticFeedbackThresholdReached by remember { mutableStateOf(false) }
    val sheetVisibility by animateFloatAsState(
        targetValue = if (isSheetFullyDragged) 0f else 1f,
        animationSpec = tween(1000)
    )

    // Snap the sheet when drag is released beyond this threshold
    val swipeThreshold = contentWidth * snapThreshold

    // Lock sheet in place once it is fully dragged
    if (isSheetFullyDragged) {
        dragEnabled = false
        onDragDone()
    }

    // Control the haptic feedback on snap threshold crossed
    if (enableHapticFeedback) {
        if (!hapticFeedbackThresholdReached) {
            if (-currentSheetOffset.value.roundToInt() > swipeThreshold.roundToInt()) {
                hapticFeedback.performHeavyImpact()
                hapticFeedbackThresholdReached = true
            }
        } else {
            if (-currentSheetOffset.value.roundToInt() <= swipeThreshold.roundToInt()) {
                hapticFeedback.performHeavyImpact()
                hapticFeedbackThresholdReached = false
            }
        }
    }

    BoxWithConstraints {
        val maxWidthPx = constraints.maxWidth.toFloat()

        Box(
            modifier = Modifier
                .width((maxWidthPx * 2).dp)
                .clip(RoundedCornerShape(16.dp))
                .onSizeChanged { size ->
                    contentWidth = size.width.toFloat()
                }
        ) {
            Box(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        scope.launch {
                            contentHeight = with(density) { coordinates.size.height.toDp() }
                        }
                    }
            ) {
                content()
            }
            val offset = currentSheetOffset.value + contentWidth


            Box(
                modifier = Modifier
                    .height(contentHeight.value.dp)
                    .offset { IntOffset(currentSheetOffset.value.roundToInt(), 0) }
                    .combinedClickable(
                        onClick = {},
                        onLongClick = {
                            onActiveLongClick()
                            if (enableHapticFeedback) hapticFeedback.performHeavyImpact()
                        },
                        onDoubleClick = {
                            scope.launch {
                                onActiveDoubleClick()
                                currentSheetOffset.animateTo(
                                    targetValue = -contentWidth,
                                    animationSpec = tween(durationMillis = 300)
                                )
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .draggable(
                        enabled = dragEnabled,
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            scope.launch {
                                currentSheetOffset.snapTo(
                                    (currentSheetOffset.value + delta).coerceIn(
                                        -maxWidthPx,
                                        0f
                                    )
                                )
                            }
                        },
                        onDragStopped = {
                            val currentOffset = currentSheetOffset.value
                            if (currentOffset.coerceAtLeast(-swipeThreshold) == currentOffset) {
                                currentSheetOffset.animateTo(
                                    targetValue = 0f,
                                    animationSpec = tween(durationMillis = 300)
                                )
                            } else {
                                currentSheetOffset.animateTo(
                                    targetValue = -maxWidthPx,
                                    animationSpec = tween(durationMillis = 300)
                                )
                            }
                        }
                    )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Transparent)
                )
            }
            Box(
                modifier = Modifier
                    .height(contentHeight.value.dp)
                    .offset { IntOffset(offset.roundToInt(), 0) }
                    .combinedClickable(
                        onClick = {},
                        onLongClick = {
                            onDoneLongClick()
                            if (enableHapticFeedback) hapticFeedback.performHeavyImpact()
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .draggable(
                        enabled = dragEnabled,
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            scope.launch {
                                currentSheetOffset.snapTo(
                                    (currentSheetOffset.value + delta).coerceIn(
                                        -maxWidthPx,
                                        0f
                                    )
                                )
                            }
                        },
                        onDragStopped = {
                            val currentOffset = currentSheetOffset.value

                            if (currentOffset.coerceAtLeast(-swipeThreshold) == currentOffset) {
                                currentSheetOffset.animateTo(
                                    targetValue = 0f,
                                    animationSpec = tween(durationMillis = 300)
                                )
                            } else {
                                currentSheetOffset.animateTo(
                                    targetValue = -maxWidthPx,
                                    animationSpec = tween(durationMillis = 300)
                                )
                            }
                        }
                    )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().alpha(sheetVisibility)
                ) {
                    sheetContent()
                }
            }
        }
    }
}

@Composable
fun getDefaultSheetContent() =
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Green)
    )
