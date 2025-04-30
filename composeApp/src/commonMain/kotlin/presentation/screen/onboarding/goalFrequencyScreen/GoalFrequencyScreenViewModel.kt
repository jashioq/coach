package presentation.screen.onboarding.goalFrequencyScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.Goal
import domain.useCase.AddGoalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GoalFrequencyScreenViewModel(
    private val addGoalUseCase: AddGoalUseCase,
) : ViewModel() {
    private val _frequency = MutableStateFlow(3f)
    val frequency = _frequency.asStateFlow()

    fun action(action: GoalFrequencyScreenAction) {
        when (action) {
            is GoalFrequencyScreenAction.SaveGoal -> {
                viewModelScope.launch {
                    addGoalUseCase.call(Goal(action.name, action.frequency.toInt()))
                }
            }
            is GoalFrequencyScreenAction.UpdateFrequency -> {
                _frequency.value = action.newValue
            }
        }
    }
}
