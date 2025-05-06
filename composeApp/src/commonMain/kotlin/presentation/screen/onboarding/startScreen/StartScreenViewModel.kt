package presentation.screen.onboarding.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.OnboardingState
import domain.useCase.EmitOnboardingFinishedUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartScreenViewModel(
    private val emitOnboardingFinishedUseCase: EmitOnboardingFinishedUseCase,
) : ViewModel() {
    private val _text = MutableStateFlow("Success!")
    val text = _text.asStateFlow()

    private val _onboardingState = MutableStateFlow(OnboardingState.LOADING)
    val onboardingState = _onboardingState.asStateFlow()

    private var currentIndex = 0

    private val textList = listOf("Progress!", "Achieving!", "Success!")

    init {
        viewModelScope.launch {
            emitOnboardingFinishedUseCase.call().onSuccess {
                it.collect { finished ->
                    if (finished) {
                        _onboardingState.value = OnboardingState.FINISHED
                    } else {
                        _onboardingState.value = OnboardingState.NOT_FINISHED
                    }
                }
            }

            while (true) {
                _text.value = textList[currentIndex]
                currentIndex = currentIndex.incrementIndex()
                delay(2000)
            }
        }
    }

    private fun Int.incrementIndex(): Int {
        val maxIndex = textList.size - 1

        return if (this >= maxIndex) {
            0
        } else {
            this + 1
        }
    }
}
