package presentation.screen.onboarding.startScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.screen.onboarding.startScreen.viewModel.StartScreenViewModel
import presentation.util.koinViewModel

@Composable
fun StartScreen(
    startScreenViewModel: StartScreenViewModel = koinViewModel(),
    onNavigateToNameScreen: () -> Unit,
) {
    val state by startScreenViewModel.state.collectAsState()

    StartScreenView(
        textIndex = state.textIndex,
        onPrimaryButtonClick = {
            onNavigateToNameScreen()
        },
    )
}
