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

class StartScreenViewModel : ViewModel() {
    private val _textIndex = MutableStateFlow(4)
    val textIndex = _textIndex.asStateFlow()

    private var currentIndex = 0

    init {
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
