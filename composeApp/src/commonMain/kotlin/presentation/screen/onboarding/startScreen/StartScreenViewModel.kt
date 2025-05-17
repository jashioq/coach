package presentation.screen.onboarding.startScreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.util.CoreViewModel

class StartScreenViewModel : CoreViewModel<StartScreenState, Unit>(
    initialState = StartScreenState(
        textIndex = 4,
    ),
) {
    init {
        viewModelScope.launch {
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

    private fun Int.incrementIndex(maxIndex: Int): Int =
        if (this >= maxIndex) {
            0
        } else {
            this + 1
        }
}
