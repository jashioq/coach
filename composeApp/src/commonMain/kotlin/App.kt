import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import co.touchlab.stately.ensureNeverFrozen

import domain.model.OnboardingState
import navigation.MainNavHost
import navigation.OnboardingNavHost
import navigation.viewModel.NavigationViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.koinViewModel

@Composable
@Preview
fun App() {
    Theme {
        KoinContext {
            GetNavHost()
        }
    }
}

@Composable
private fun GetNavHost(
    navigationViewModel: NavigationViewModel = koinViewModel(),
) {
    val onboardingState by navigationViewModel.onboardingState.collectAsState()

    when(onboardingState) {
        OnboardingState.LOADING -> {
            // Do nothing
        }
        OnboardingState.FINISHED -> MainNavHost()
        OnboardingState.NOT_FINISHED -> OnboardingNavHost()
    }
}
