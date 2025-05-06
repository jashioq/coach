package presentation.screen.onboarding.goalNameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun GoalNameScreen(
    goalNameScreenViewModel: GoalNameScreenViewModel = koinViewModel(),
    onNavigateToGoalFrequencyScreen: (String) -> Unit,
    userName: String,
) {
    val goalName by goalNameScreenViewModel.goalName.collectAsState()
    val goalNamePlaceholder by goalNameScreenViewModel.goalNamePlaceholder.collectAsState()

    GoalNameScreenView(
        textFieldValue = goalName,
        textFieldPlaceholder = goalNamePlaceholder,
        userName = userName,
        onInputDone = { onNavigateToGoalFrequencyScreen(goalName.trim()) },
        onTextFieldValueChange = {
            goalNameScreenViewModel.action(
                GoalNameScreenAction.UpdateGoalName(it),
            )
        },
    )
}
