package presentation.screen.onboarding.startScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun StartScreen(
    startScreenViewModel: StartScreenViewModel = koinViewModel(),
    onNavigateToNameScreen: () -> Unit,
) {
    val textIndex by startScreenViewModel.textIndex.collectAsState()

    StartScreenView(
        textIndex = textIndex,
        onPrimaryButtonClick = {
            onNavigateToNameScreen()
        },
    )
}
