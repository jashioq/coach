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
    val state by goalFrequencyScreenViewModel.state.collectAsState()

    GoalFrequencyScreenView(
        goalName = goalName,
        sliderPosition = state.frequency,
        onPositionChange = {
            goalFrequencyScreenViewModel.sendAction(
                GoalFrequencyScreenAction.UpdateFrequency(it),
            )
        },
        onGoalSave = {
            goalFrequencyScreenViewModel.sendAction(
                GoalFrequencyScreenAction.SaveGoal(
                    name = goalName,
                ),
            )
        },
        progressIndicatorState = progressIndicatorState,
    )
}
