package presentation.screen.onboarding.goalFrequencyScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.screen.onboarding.goalFrequencyScreen.viewModel.GoalFrequencyScreenViewModel
import presentation.util.koinViewModel

@Composable
fun GoalFrequencyScreen(
    goalFrequencyScreenViewModel: GoalFrequencyScreenViewModel = koinViewModel(),
    goalName: String,
    progressIndicatorState: ProgressIndicatorState,
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
        onGoalSave = {
            goalFrequencyScreenViewModel.action(
                GoalFrequencyScreenAction.SaveGoal(
                    name = goalName,
                    frequency = goalFrequency,
                ),
            )
        },
        progressIndicatorState = progressIndicatorState,
    )
}
