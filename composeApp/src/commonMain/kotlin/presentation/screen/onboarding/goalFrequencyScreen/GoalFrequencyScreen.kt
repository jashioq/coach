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
    onFinishOnboarding: () -> Unit,
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
            onFinishOnboarding()
        },
        progressIndicatorState = progressIndicatorState,
    )
}
