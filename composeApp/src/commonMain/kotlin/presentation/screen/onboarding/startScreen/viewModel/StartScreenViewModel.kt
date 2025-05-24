package presentation.screen.onboarding.startScreen.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.onboarding.startScreen.StartScreenState
import presentation.util.CoreViewModel
import util.Logger
import util.extension.incrementIndex

class StartScreenViewModel(
    scope: CoroutineScope? = null,
    logger: Logger? = null,
) : CoreViewModel<StartScreenState, Unit>(
    initialState = StartScreenState(
        textIndex = 4,
    ),
    scope = scope,
    logger = logger,
) {
    init {
        vmScope.launch {
            var currentIndex = 0
            while (true) {
                _state.update {
                    it.copy(
                        textIndex = currentIndex,
                    )
                }
                currentIndex = currentIndex.incrementIndex(4)
                delay(2000)
            }
        }
    }
}
