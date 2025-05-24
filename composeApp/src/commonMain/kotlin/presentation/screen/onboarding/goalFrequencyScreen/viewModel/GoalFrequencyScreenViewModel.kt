package presentation.screen.onboarding.goalFrequencyScreen.viewModel

import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenAction
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenState
import presentation.util.CoreViewModel
import util.Logger

class GoalFrequencyScreenViewModel(
    private val addGoalUseCase: UseCase<Pair<String, Int>, Unit>,
    private val setOnboardingFinishedUseCase: UseCase<Boolean, Unit>,
    scope: CoroutineScope? = null,
    logger: Logger? = null,
) : CoreViewModel<GoalFrequencyScreenState, GoalFrequencyScreenAction>(
    initialState = GoalFrequencyScreenState(
        frequency = 3f,
    ),
    scope = scope,
    logger = logger,
) {
    fun dispatch(action: GoalFrequencyScreenAction) =
        action.process {
            when (it) {
                is GoalFrequencyScreenAction.SaveGoal -> {
                    vmScope.launch {
                        addGoalUseCase.call(it.name to state.value.frequency.toInt())
                        setOnboardingFinishedUseCase.call(true)
                    }
                }

                is GoalFrequencyScreenAction.UpdateFrequency -> {
                    _state.update { state ->
                        state.copy(
                            frequency = it.newValue,
                        )
                    }
                }
            }
        }
}
