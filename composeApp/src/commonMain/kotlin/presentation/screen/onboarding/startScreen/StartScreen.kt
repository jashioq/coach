package presentation.screen.onboarding.startScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import domain.model.OnboardingState
import presentation.koinViewModel

@Composable
fun StartScreen(
    startScreenViewModel: StartScreenViewModel = koinViewModel(),
    onNavigateToNameScreen: () -> Unit,
    onOnboardingFinished: () -> Unit,
) {
    val text by startScreenViewModel.text.collectAsState()
    val onboardingState by startScreenViewModel.onboardingState.collectAsState()

    when (onboardingState) {
        OnboardingState.LOADING -> {
            // Do nothing
        }
        OnboardingState.FINISHED -> onOnboardingFinished()
        OnboardingState.NOT_FINISHED -> {
            StartScreenView(
                text = text,
                onPrimaryButtonClick = {
                    onNavigateToNameScreen()
                },
            )
        }
    }
}
