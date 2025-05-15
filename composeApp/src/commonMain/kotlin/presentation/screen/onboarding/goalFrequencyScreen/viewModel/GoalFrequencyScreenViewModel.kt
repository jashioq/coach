package presentation.screen.onboarding.goalFrequencyScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.util.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenAction

class GoalFrequencyScreenViewModel(
    private val addGoalUseCase: UseCase<Pair<String, Int>, Unit>,
    private val setOnboardingFinishedUseCase: UseCase<Boolean, Unit>,
) : ViewModel() {
    private val _frequency = MutableStateFlow(3f)
    val frequency = _frequency.asStateFlow()

    fun action(action: GoalFrequencyScreenAction) {
        when (action) {
            is GoalFrequencyScreenAction.SaveGoal -> {
                println("dupa SaveGoal")

                viewModelScope.launch {
                    addGoalUseCase.call(action.name to action.frequency.toInt())
                    setOnboardingFinishedUseCase.call(true)
                    println("dupa setOnboardingFinishedUseCase")
                }
            }

            is GoalFrequencyScreenAction.UpdateFrequency -> {
                _frequency.value = action.newValue
            }
        }
    }
}
