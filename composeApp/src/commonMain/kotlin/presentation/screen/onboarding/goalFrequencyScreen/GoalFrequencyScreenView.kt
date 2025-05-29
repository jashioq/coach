package presentation.screen.onboarding.goalFrequencyScreen

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import coach.composeapp.generated.resources.Res
import coach.composeapp.generated.resources.five_days
import coach.composeapp.generated.resources.four_days
import coach.composeapp.generated.resources.goal_frequency_screen_description
import coach.composeapp.generated.resources.goal_frequency_screen_sheet_button
import coach.composeapp.generated.resources.goal_frequency_screen_sheet_text
import coach.composeapp.generated.resources.goal_frequency_screen_sheet_title
import coach.composeapp.generated.resources.goal_frequency_screen_title
import coach.composeapp.generated.resources.one_day
import coach.composeapp.generated.resources.per_week
import coach.composeapp.generated.resources.save_button
import coach.composeapp.generated.resources.seven_days
import coach.composeapp.generated.resources.six_days
import coach.composeapp.generated.resources.three_days
import coach.composeapp.generated.resources.two_days
import coach.composeapp.generated.resources.zero_days
import org.jetbrains.compose.resources.stringResource
import presentation.compose.component.button.PrimaryButton
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.compose.component.progress.SharedProgressIndicator
import presentation.compose.component.slider.StepSlider
import presentation.compose.component.text.Text
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalFrequencyScreenView(
    modifier: Modifier = Modifier,
    goalName: String,
    sliderPosition: Float,
    onPositionChange: (Float) -> Unit,
    onGoalSave: () -> Unit,
    progressIndicatorState: ProgressIndicatorState,
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Black,
        contentColor = Color.White,
        disabledContentColor = Color.White,
    )

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
                    color = Color.Black,
                    text = stringResource(Res.string.goal_frequency_screen_sheet_title),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    color = Color.Black,
                    text = stringResource(Res.string.goal_frequency_screen_sheet_text),
                    lineHeight = 24.sp,
                )

                Spacer(modifier = Modifier.height(32.dp))

                PrimaryButton(
                    colors = sheetButtonColors,
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
            SharedProgressIndicator(
                modifier = Modifier.padding(16.dp),
                progressIndicatorState = progressIndicatorState,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(Res.string.goal_frequency_screen_title, goalName),
            fontSize = 48.sp,
            lineHeight = 64.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(Res.string.goal_frequency_screen_description),
        )

        Column(
            modifier = modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = sliderPosition.getInfoText(),
                fontSize = 32.sp,
                lineHeight = 44.sp,
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
    } + stringResource(Res.string.per_week)

@Composable
private fun Float.GetSliderColors() =
    when (this.roundToInt()) {
        0 -> SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            activeTrackColor = MaterialTheme.colorScheme.inversePrimary,
            inactiveTickColor = MaterialTheme.colorScheme.inversePrimary,
            activeTickColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        )

        in 0..3 -> SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = MaterialTheme.colorScheme.primary,
            inactiveTickColor = MaterialTheme.colorScheme.primary,
            activeTickColor = MaterialTheme.colorScheme.inversePrimary,
            inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        )

        in 3..5 -> SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = Color(0, 110, 37),
            inactiveTickColor = Color(0, 110, 37),
            activeTickColor = MaterialTheme.colorScheme.inversePrimary,
            inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        )

        6 -> SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = Color(143, 100, 0),
            inactiveTickColor = Color(143, 100, 0),
            activeTickColor = MaterialTheme.colorScheme.inversePrimary,
            inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        )

        else -> SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = Color(166, 50, 50),
            inactiveTickColor = Color(166, 50, 50),
            activeTickColor = MaterialTheme.colorScheme.inversePrimary,
        )
    }
