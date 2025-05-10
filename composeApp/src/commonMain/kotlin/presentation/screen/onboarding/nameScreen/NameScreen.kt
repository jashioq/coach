package presentation.screen.onboarding.nameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.compose.component.progress.ProgressIndicatorState
import presentation.screen.onboarding.nameScreen.viewModel.NameScreenViewModel
import presentation.util.koinViewModel

@Composable
fun NameScreen(
    nameScreenViewModel: NameScreenViewModel = koinViewModel(),
    onNavigateToGoalNameScreen: (String) -> Unit,
    progressIndicatorState: ProgressIndicatorState,
) {
    val name by nameScreenViewModel.name.collectAsState()

    NameScreenView(
        textFieldValue = name,
        onInputDone = {
            nameScreenViewModel.action(
                NameScreenAction.SaveName,
            )
            onNavigateToGoalNameScreen(name.trim())
        },
        onTextFieldValueChange = {
            nameScreenViewModel.action(
                NameScreenAction.UpdateName(it),
            )
        },
        progressIndicatorState = progressIndicatorState,
    )
}
