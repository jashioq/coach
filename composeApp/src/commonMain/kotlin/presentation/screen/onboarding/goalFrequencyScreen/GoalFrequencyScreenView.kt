package presentation.screen.onboarding.goalFrequencyScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import presentation.compose.component.button.PrimaryButton
import presentation.compose.component.panel.RectanglePopup
import presentation.compose.component.panel.rememberPopupState
import presentation.compose.component.progress.CircularProgressIndicator
import presentation.compose.component.slider.StepSlider
import kotlin.math.roundToInt

@Composable
fun GoalFrequencyScreenView(
    modifier: Modifier = Modifier,
    goalName: String,
    sliderPosition: Float,
    onPositionChange: (Float) -> Unit,
) {
    val primaryText = "How often do you want to $goalName?"
    val secondaryText = "You can change this later"
    val infoText = sliderPosition.getInfoText()

    var progress by remember { mutableStateOf(0.5f) }
    val progressAnimDuration = 1_500

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )

    var popupVisible by rememberPopupState()

    LaunchedEffect(LocalLifecycleOwner.current) {
        progress = 0.75f
    }

    RectanglePopup(
        visibleState = mutableStateOf(popupVisible),
        onDismissRequest = { popupVisible = false },
        content = {
            Text("Your content here")
        },
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .displayCutoutPadding()
            .navigationBarsPadding()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp),
                progress = progressAnimation,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier,
            text = primaryText,
            fontSize = 64.sp,
            lineHeight = 72.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier,
            text = secondaryText,
            fontSize = 24.sp,
            lineHeight = 36.sp,
        )

        Column(
            modifier = modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = Modifier,
                text = infoText,
                fontSize = 36.sp,
                lineHeight = 48.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(32.dp))

            StepSlider(
                position = sliderPosition,
                onPositionChange = onPositionChange,
                colors = sliderPosition.GetSliderColors(),
            )
        }
        PrimaryButton(
            text = "SAVE",
            isEnabled = sliderPosition != 0f,
        )
    }
}

private fun Float.getInfoText() =
    when (this.roundToInt()) {
        0 -> "Zero days a week"
        1 -> "One day a week"
        2 -> "Two days a week"
        3 -> "Three days a week"
        4 -> "Four days a week"
        5 -> "Five days a week"
        6 -> "Six days a week"
        else -> "Seven days a week"
    }

@Composable
private fun Float.GetSliderColors() =
    when (this) {
        0f -> SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color.LightGray,
            inactiveTickColor = Color.LightGray,
            inactiveTrackColor = Color.LightGray.copy(alpha = 0.5f),
        )

        in 0f..3f -> SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color.Black,
            inactiveTickColor = Color.Black,
            activeTickColor = Color.White,
            inactiveTrackColor = Color.Gray,
        )

        in 3f..5f -> SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color.Green,
            inactiveTickColor = Color.Green,
            activeTickColor = Color.White,
            inactiveTrackColor = Color.Gray,
        )

        6f -> SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color.Yellow,
            inactiveTickColor = Color.Yellow,
            activeTickColor = Color.White,
            inactiveTrackColor = Color.Gray,
        )

        else -> SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color.Red,
            inactiveTickColor = Color.Red,
            activeTickColor = Color.White,
            inactiveTrackColor = Color.Gray,
        )
    }
