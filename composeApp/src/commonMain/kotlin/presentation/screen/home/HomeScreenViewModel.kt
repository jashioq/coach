package presentation.screen.home

import androidx.lifecycle.viewModelScope
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitUserNamePreferenceUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.util.CoreViewModel

class HomeScreenViewModel(
    private val emitUserNamePreferenceUseCase: EmitUserNamePreferenceUseCase,
    private val emitAllGoalsUseCase: EmitAllGoalsUseCase,
) : CoreViewModel<HomeScreenState, Unit>(
    initialState = HomeScreenState(
        userName = "",
        goalId = "",
        goalName = "",
        goalFrequency = "",
    ),
) {
    init {
        viewModelScope.launch {
            emitUserNamePreferenceUseCase.call(value = Unit).onSuccess {
                it.collect { name ->
                    _state.update { state ->
                        state.copy(
                            userName = name,
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            emitAllGoalsUseCase.call(value = Unit).onSuccess {
                it.collect { goals ->
                    _state.update { state ->
                        state.copy(
                            goalId = goals.first().id.toString(),
                            goalName = goals.first().name,
                            goalFrequency = goals.first().frequency.toString(),
                        )
                    }
                }
            }
        }
    }
}
