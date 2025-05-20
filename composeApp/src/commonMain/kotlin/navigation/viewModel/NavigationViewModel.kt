package navigation.viewModel

import domain.model.OnboardingState
import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.util.CoreViewModel

class NavigationViewModel(
    private val emitOnboardingFinishedUseCase: UseCase<Unit, Flow<Boolean>>,
    scope: CoroutineScope? = null,
) : CoreViewModel<Unit, Unit>(
    initialState = Unit,
    scope = scope,
) {
    private val _onboardingState = MutableStateFlow(OnboardingState.LOADING)
    val onboardingState = _onboardingState.asStateFlow()

    init {
        vmScope.launch {
            emitOnboardingFinishedUseCase.call(value = Unit).onSuccess {
                it.collect { finished ->
                    _onboardingState.value =
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
