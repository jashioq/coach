package presentation.screen.onboarding.goalFrequencyScreen.viewModel

import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenAction
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenState
import presentation.util.CoreViewModel
import util.Logger

/**
 * View model used for handling goal frequency screen state and actions.
 * @param addGoalUseCase a [UseCase] used for adding a goal.
 * @param setOnboardingFinishedUseCase a [UseCase] used for setting onboarding finished status.
 */
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
    override fun GoalFrequencyScreenAction.process() {
        when (val action = this@process) {
            is GoalFrequencyScreenAction.SaveGoal -> {
                vmScope.launch {
                    addGoalUseCase.call(action.name to state.value.frequency.toInt())
                    setOnboardingFinishedUseCase.call(true)
                }
            }

            is GoalFrequencyScreenAction.UpdateFrequency -> {
                stateFlow.update { state ->
                    state.copy(
                        frequency = action.newValue,
                    )
                }
            }
        }
    }
}
