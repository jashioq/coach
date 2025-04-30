package presentation.screen.onboarding.nameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun NameScreen(
    nameScreenViewModel: NameScreenViewModel = koinViewModel(),
    onNavigateToGoalNameScreen: (String) -> Unit,
) {
    val name by nameScreenViewModel.name.collectAsState()

    NameScreenView(
        textFieldValue = name,
        onInputDone = {
            nameScreenViewModel.action(
                NameScreenAction.SaveName,
            )
            onNavigateToGoalNameScreen(name)
        },
        onTextFieldValueChange = {
            nameScreenViewModel.action(
                NameScreenAction.UpdateName(it),
            )
        },
    )
}
