package presentation.screen.onboarding.goalFrequencyScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.koinViewModel

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
            goalFrequencyScreenViewModel.dispatch(
                GoalFrequencyScreenAction.UpdateFrequency(it),
            )
        },
        onGoalSave = {
            goalFrequencyScreenViewModel.dispatch(
                GoalFrequencyScreenAction.SaveGoal(
                    name = goalName,
                    frequency = state.frequency,
                ),
            )
        },
        progressIndicatorState = progressIndicatorState,
    )
}
