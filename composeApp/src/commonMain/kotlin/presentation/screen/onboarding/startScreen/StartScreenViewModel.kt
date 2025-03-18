package presentation.screen.onboarding.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartScreenViewModel() : ViewModel() {
    private val _text = MutableStateFlow("Progress!")
    val text = _text.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                delay(3000)
                _text.value = "Success!"
                delay(3000)
                _text.value = "Achieving!"
                delay(3000)
                _text.value = "Progress!"
            }
        }
    }
}
