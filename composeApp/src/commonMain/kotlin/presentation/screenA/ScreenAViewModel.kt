package presentation.screenA

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.useCase.EmitCounterPreferenceUseCase
import domain.useCase.SetCounterPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class ScreenAViewModel(
    private val setCounterPreferenceUseCase: SetCounterPreferenceUseCase,
    private val emitCounterPreferenceUseCase: EmitCounterPreferenceUseCase,
) : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    init {
        viewModelScope.launch {
            emitCounterPreferenceUseCase.call().collect{ value ->
                _counter.value = value
            }
        }
    }

    fun action(action: ScreenAAction) {
        when (action) {
            ScreenAAction.IncrementCounter -> {
                viewModelScope.launch {
                    setCounterPreferenceUseCase.call(counter.value + 1)
                }
            }
            is ScreenAAction.SetCounter -> {
                viewModelScope.launch {
                    setCounterPreferenceUseCase.call(0)
                }
            }
            else -> { /*Do nothing*/ }
        }
    }
}
