package presentation.screen.onboarding.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartScreenViewModel() : ViewModel() {
    private val _text = MutableStateFlow("Success!")
    val text = _text.asStateFlow()

    private var currentIndex = 0

    private val textList = listOf("Progress!", "Achieving!", "Success!")

    init {
        viewModelScope.launch {
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
