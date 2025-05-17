package presentation.screen.onboarding.nameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.koinViewModel

@Composable
fun NameScreen(
    nameScreenViewModel: NameScreenViewModel = koinViewModel(),
    onNavigateToGoalNameScreen: (String) -> Unit,
    progressIndicatorState: ProgressIndicatorState,
) {
    val state by nameScreenViewModel.state.collectAsState()

    NameScreenView(
        textFieldValue = state.name,
        onInputDone = {
            nameScreenViewModel.dispatch(
                NameScreenAction.SaveName,
            )
            onNavigateToGoalNameScreen(state.name.trim())
        },
        onTextFieldValueChange = {
            nameScreenViewModel.dispatch(
                NameScreenAction.UpdateName(it),
            )
        },
        progressIndicatorState = progressIndicatorState,
    )
}
