package presentation.compose.component.preview

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.check_mark
import coach.composeapp.generated.resources.dot_fill
import coach.composeapp.generated.resources.dot_hollow
import coach.composeapp.generated.resources.dot_hollow_crossed
import domain.model.GoalState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.minus
import org.jetbrains.compose.resources.painterResource
import presentation.compose.component.interaction.SwipeInteraction
import presentation.compose.component.text.Text
import presentation.util.getLocalDateTime
import presentation.util.getWeekBounds

@Composable
fun GoalPreview(
    modifier: Modifier = Modifier,
    goalState: GoalState = GoalState.ACTIVE,
    goalName: String,
    goalCompletions: List<LocalDateTime>,
    onGoalStateChange: (GoalState) -> Unit = {},
    onOptionsOpen: () -> Unit = {},
) {
    val today = getLocalDateTime().date
    val weekBounds = getWeekBounds()
    val completionsThisWeek =
        goalCompletions.filter { it.date in weekBounds.first..weekBounds.second }

    val monday = weekBounds.first
    val transformedCompletions = completionsThisWeek.map {
        it.date.minus(monday).days
    }

    val currentDay = today.minus(monday).days

    val animationScope = rememberCoroutineScope()

    val sheetOffset = remember { Animatable(0f) }

    var hapticsEnabled by remember { mutableStateOf(true) }
    var dragEnabled by remember { mutableStateOf(true) }
    var sheetVisibilityTarget by remember { mutableStateOf(1f) }

    val colorAnimationTime = 500
    val sheetVisibility by animateFloatAsState(
        targetValue = sheetVisibilityTarget,
        animationSpec = tween(colorAnimationTime),
    )

    val mainColor by animateColorAsState(
        if (goalState == GoalState.ACTIVE) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        },
        animationSpec = tween(colorAnimationTime),
    )

    val snapThreshold = -0.4f

    if (sheetOffset.value == -1f) {
        onGoalStateChange(GoalState.DONE)
        hapticsEnabled = false
        animationScope.launch {
            sheetVisibilityTarget = 0f
            delay(colorAnimationTime.toLong())
            sheetOffset.snapTo(0f)
            sheetVisibilityTarget = 1f
            delay(colorAnimationTime.toLong())
            hapticsEnabled = true
        }
    }

    dragEnabled = when (goalState) {
        GoalState.ACTIVE -> {
            true
        }

        GoalState.DONE -> {
            false
        }
    }

    Box(
        modifier = modifier
            .padding(16.dp)
            .border(3.dp, mainColor, RoundedCornerShape(16.dp)),
    ) {
        SwipeInteraction(
            onDragDone = { onGoalStateChange(GoalState.DONE) },
            onActiveLongClick = onOptionsOpen,
            onActiveDoubleClick = {
                if (dragEnabled) {
                    animationScope.launch {
                        sheetOffset.animateTo(
                            targetValue = -1f,
                            animationSpec = tween(300),
                        )
                    }
                }
            },
            onDoneLongClick = onOptionsOpen,
            sheetOffset = sheetOffset.value,
            snapThreshold = snapThreshold,
            sheetVisibility = sheetVisibility,
            onSheetOffsetChanged = {
                if (dragEnabled) {
                    animationScope.launch {
                        sheetOffset.snapTo(
                            targetValue = it,
                        )
                    }
                }
            },
            onSheetDragStopped = {
                animationScope.launch {
                    if (sheetOffset.value.coerceAtLeast(snapThreshold) == sheetOffset.value) {
                        sheetOffset.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(300),
                        )
                    } else {
                        sheetOffset.animateTo(
                            targetValue = -1f,
                            animationSpec = tween(300),
                        )
                    }
                }
            },
            enableHapticFeedback = hapticsEnabled,
            scope = animationScope,
            content = {
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = goalName,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        color = mainColor,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        (0..6).map {
                            val done = transformedCompletions.contains(it)
                            val upcoming = it > currentDay
                            val isToday = it == currentDay

                            DotIcon(done, upcoming, isToday, goalState, mainColor)
                        }
                    }
                }
            },
            sheetContent = {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(Color.Green),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.check_mark),
                        tint = Color.White,
                        contentDescription = "Check",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(32.dp.scaleWithOffset(sheetOffset.value))
                            .alpha(0f.growWithOffset(sheetOffset.value)),
                    )
                }
            },
        )
    }
}

private fun Dp.scaleWithOffset(offset: Float): Dp =
    this * ((offset / -1f) + 1f)

private fun Float.growWithOffset(offset: Float): Float =
    this - (offset * 1.2).toFloat()

@Composable
private fun DotIcon(
    done: Boolean,
    upcoming: Boolean,
    isToday: Boolean,
    goalState: GoalState,
    mainColor: Color,
) {
    val painter = if (done) {
        painterResource(Res.drawable.dot_fill)
    } else if (upcoming || isToday) {
        painterResource(Res.drawable.dot_hollow)
    } else {
        painterResource(Res.drawable.dot_hollow_crossed)
    }

    val tint = if (upcoming || goalState == GoalState.DONE) {
        mainColor.copy(alpha = 0.2f)
    } else {
        mainColor
    }

    Icon(
        modifier = Modifier.size(42.dp),
        painter = painter,
        tint = tint,
        contentDescription = "Icon",
    )
}
