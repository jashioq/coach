package presentation.screen.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitUserNamePreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val emitUserNamePreferenceUseCase: EmitUserNamePreferenceUseCase,
    private val emitAllGoalsUseCase: EmitAllGoalsUseCase,
) : ViewModel() {
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _goalId = MutableStateFlow("")
    val goalId = _goalId.asStateFlow()

    private val _goalName = MutableStateFlow("")
    val goalName = _goalName.asStateFlow()

    private val _goalFrequency = MutableStateFlow("")
    val goalFrequency = _goalFrequency.asStateFlow()

    init {
        viewModelScope.launch {
            emitUserNamePreferenceUseCase.call(value = Unit).onSuccess {
                it.collect { name ->
                    _userName.value = name
                }
            }
        }
        viewModelScope.launch {
            emitAllGoalsUseCase.call(value = Unit).onSuccess {
                it.collect { goals ->
                    _goalId.value = goals.first().id.toString()
                    _goalName.value = goals.first().name
                    _goalFrequency.value = goals.first().frequency.toString()
                }
            }
        }
    }
}
