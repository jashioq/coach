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
    val goalName by goalNameScreenViewModel.goalName.collectAsState()
    val goalNamePlaceholderIndex by goalNameScreenViewModel.goalNamePlaceholderIndex.collectAsState()

    GoalNameScreenView(
        textFieldValue = goalName,
        textFieldPlaceholderIndex = goalNamePlaceholderIndex,
        userName = userName,
        onInputDone = { onNavigateToGoalFrequencyScreen(goalName.trim()) },
        onTextFieldValueChange = {
            goalNameScreenViewModel.action(
                GoalNameScreenAction.UpdateGoalName(it),
            )
        },
        progressIndicatorState = progressIndicatorState,
    )
}
