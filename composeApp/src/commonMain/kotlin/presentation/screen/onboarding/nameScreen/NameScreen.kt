package presentation.screen.onboarding.nameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun NameScreen(
    nameScreenViewModel: NameScreenViewModel = koinViewModel(),
    onNavigateToGoalSelectionScreen: () -> Unit,
) {
    val name by nameScreenViewModel.name.collectAsState()

    NameScreenView(
        textFieldValue = name,
        onInputDone = {
            nameScreenViewModel.action(
                NameScreenAction.SaveName,
            )
            onNavigateToGoalSelectionScreen()
        },
        onTextFieldValueChange = {
            nameScreenViewModel.action(
                NameScreenAction.UpdateName(it),
            )
        },
    )
}
