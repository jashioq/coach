package navigation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.OnboardingState
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NavigationViewModel(
    private val emitOnboardingFinishedUseCase: UseCase<Unit, Flow<Boolean>>,
) : ViewModel() {
    private val _onboardingState = MutableStateFlow(OnboardingState.LOADING)
    val onboardingState = _onboardingState.asStateFlow()

    init {
        viewModelScope.launch {
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
