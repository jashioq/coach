package presentation.screen.onboarding.startScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import domain.model.OnboardingState
import presentation.screen.onboarding.startScreen.viewModel.StartScreenViewModel
import presentation.util.koinViewModel

@Composable
fun StartScreen(
    startScreenViewModel: StartScreenViewModel = koinViewModel(),
    onNavigateToNameScreen: () -> Unit,
    onOnboardingFinished: () -> Unit,
) {
    val textIndex by startScreenViewModel.textIndex.collectAsState()
    val onboardingState by startScreenViewModel.onboardingState.collectAsState()

    when (onboardingState) {
        OnboardingState.LOADING -> {
            // Do nothing
        }
        OnboardingState.FINISHED -> onOnboardingFinished()
        OnboardingState.NOT_FINISHED -> {
            StartScreenView(
                textIndex = textIndex,
                onPrimaryButtonClick = {
                    onNavigateToNameScreen()
                },
            )
        }
    }
}
