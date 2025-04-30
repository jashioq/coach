package presentation.screen.onboarding.goalFrequencyScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun GoalFrequencyScreen(
    goalFrequencyScreenViewModel: GoalFrequencyScreenViewModel = koinViewModel(),
    goalName: String,
) {
    val goalFrequency by goalFrequencyScreenViewModel.frequency.collectAsState()

    GoalFrequencyScreenView(
        goalName = goalName,
        sliderPosition = goalFrequency,
        onPositionChange = {
            goalFrequencyScreenViewModel.action(
                GoalFrequencyScreenAction.UpdateFrequency(it),
            )
        },
    )
}
