package presentation.screen.onboarding.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.OnboardingState
import domain.util.UseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartScreenViewModel(
    private val emitOnboardingFinishedUseCase: UseCase<Unit, Flow<Boolean>>,
) : ViewModel() {
    private val _textIndex = MutableStateFlow(4)
    val textIndex = _textIndex.asStateFlow()

    private val _onboardingState = MutableStateFlow(OnboardingState.LOADING)
    val onboardingState = _onboardingState.asStateFlow()

    private var currentIndex = 0

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
        viewModelScope.launch {
            while (true) {
                _textIndex.value = currentIndex
                currentIndex = currentIndex.incrementIndex(4)
                delay(2000)
            }
        }
    }

    private fun Int.incrementIndex(maxIndex: Int): Int =
        if (this >= maxIndex) {
            0
        } else {
            this + 1
        }
}
