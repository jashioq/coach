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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.five_days
import coach.composeapp.generated.resources.four_days
import coach.composeapp.generated.resources.goal_frequency_screen_description
import coach.composeapp.generated.resources.goal_frequency_screen_sheet_button
import coach.composeapp.generated.resources.goal_frequency_screen_sheet_text
import coach.composeapp.generated.resources.goal_frequency_screen_sheet_title
import coach.composeapp.generated.resources.goal_frequency_screen_title
import coach.composeapp.generated.resources.one_day
import coach.composeapp.generated.resources.save_button
import coach.composeapp.generated.resources.seven_days
import coach.composeapp.generated.resources.six_days
import coach.composeapp.generated.resources.three_days
import coach.composeapp.generated.resources.two_days
import coach.composeapp.generated.resources.zero_days
import org.jetbrains.compose.resources.stringResource
import presentation.compose.component.button.PrimaryButton
import presentation.compose.component.progress.CircularProgressIndicator
import presentation.compose.component.slider.StepSlider
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalFrequencyScreenView(
    modifier: Modifier = Modifier,
    goalName: String,
    sliderPosition: Float,
    onPositionChange: (Float) -> Unit,
    onGoalSave: () -> Unit,
) {
    var progress by remember { mutableStateOf(0.5f) }
    val progressAnimDuration = 1_500

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(LocalLifecycleOwner.current) {
        progress = 0.75f
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(Res.string.goal_frequency_screen_sheet_title),
                    fontSize = 36.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier,
                    text = stringResource(Res.string.goal_frequency_screen_sheet_text),
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                )

                Spacer(modifier = Modifier.height(32.dp))

                PrimaryButton(
                    text = stringResource(Res.string.goal_frequency_screen_sheet_button),
                    isEnabled = sliderPosition != 0f,
                    onClick = onGoalSave,
                )
            }
        }
    }

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
            text = stringResource(Res.string.goal_frequency_screen_title, goalName),
            fontSize = 64.sp,
            lineHeight = 72.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier,
            text = stringResource(Res.string.goal_frequency_screen_description),
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
                text = sliderPosition.getInfoText(),
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
            modifier = Modifier
                .padding(bottom = 32.dp),
            text = stringResource(Res.string.save_button),
            isEnabled = sliderPosition != 0f,
            onClick = {
                if (sliderPosition.roundToInt() >= 6) showBottomSheet = true else onGoalSave()
            },
        )
    }
}

@Composable
private fun Float.getInfoText() =
    when (this.roundToInt()) {
        0 -> stringResource(Res.string.zero_days)
        1 -> stringResource(Res.string.one_day)
        2 -> stringResource(Res.string.two_days)
        3 -> stringResource(Res.string.three_days)
        4 -> stringResource(Res.string.four_days)
        5 -> stringResource(Res.string.five_days)
        6 -> stringResource(Res.string.six_days)
        else -> stringResource(Res.string.seven_days)
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
            activeTrackColor = Color(0, 110, 37),
            inactiveTickColor = Color(0, 110, 37),
            activeTickColor = Color.White,
            inactiveTrackColor = Color.Gray,
        )

        6f -> SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color(143, 100, 0),
            inactiveTickColor = Color(143, 100, 0),
            activeTickColor = Color.White,
            inactiveTrackColor = Color.Gray,
        )

        else -> SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color(166, 50, 50),
            inactiveTickColor = Color(166, 50, 50),
            activeTickColor = Color.White,
            inactiveTrackColor = Color.Gray,
        )
    }
