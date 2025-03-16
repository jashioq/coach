package presentation.screenB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.Goal
import domain.useCase.EmitAllGoalsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScreenBViewModel(
    private val emitAllGoalsUseCase: EmitAllGoalsUseCase,
) : ViewModel() {
    private val _goals = MutableStateFlow(listOf<Goal>())
    val goals = _goals.asStateFlow()

    init {
        viewModelScope.launch {
            emitAllGoalsUseCase.call().getOrNull()?.collect { goals ->
                _goals.value = goals
            }
        }
    }
}
