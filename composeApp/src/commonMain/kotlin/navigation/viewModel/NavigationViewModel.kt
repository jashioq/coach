package navigation.viewModel

import domain.model.OnboardingState
import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.util.CoreViewModel
import util.Logger

class NavigationViewModel(
    private val emitOnboardingFinishedUseCase: UseCase<Unit, Flow<Boolean>>,
    scope: CoroutineScope? = null,
    logger: Logger? = null,
) : CoreViewModel<OnboardingState, Unit>(
    initialState = OnboardingState.LOADING,
    scope = scope,
    logger = logger,
) {
    init {
        vmScope.launch {
            emitOnboardingFinishedUseCase.call(value = Unit).onSuccess {
                it.collect { finished ->
                    _state.update {
                        if (finished) {
                            OnboardingState.FINISHED
                        } else {
                            OnboardingState.NOT_FINISHED
                        }
                    }
                }
            }
        }
    }
}
