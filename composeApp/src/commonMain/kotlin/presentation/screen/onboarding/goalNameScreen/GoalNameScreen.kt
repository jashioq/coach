package presentation.screen.onboarding.goalNameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.screen.onboarding.goalNameScreen.viewModel.GoalNameScreenViewModel
import presentation.util.koinViewModel

@Composable
fun GoalNameScreen(
    goalNameScreenViewModel: GoalNameScreenViewModel = koinViewModel(),
    onNavigateToGoalFrequencyScreen: (String) -> Unit,
    userName: String,
    progressIndicatorState: ProgressIndicatorState,
) {
    val state by goalNameScreenViewModel.state.collectAsState()

    GoalNameScreenView(
        textFieldValue = state.goalName,
        textFieldPlaceholderIndex = state.goalNamePlaceholderIndex,
        userName = userName,
        onInputDone = { onNavigateToGoalFrequencyScreen(state.goalName.trim()) },
        onTextFieldValueChange = {
            goalNameScreenViewModel.dispatch(
                GoalNameScreenAction.UpdateGoalName(it),
            )
        },
        progressIndicatorState = progressIndicatorState,
    )
}
