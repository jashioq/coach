package presentation.screen.onboarding.goalNameScreen.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenAction
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenState
import presentation.util.CoreViewModel
import util.Logger
import util.extension.incrementIndex

/**
 * View model used for handling goal name screen state and actions. It circulates through
 * placeholder text indexes indefinitely to display different placeholders in the text field.
 */
class GoalNameScreenViewModel(
    scope: CoroutineScope? = null,
    logger: Logger? = null,
) : CoreViewModel<GoalNameScreenState, GoalNameScreenAction>(
    initialState = GoalNameScreenState(
        goalName = "",
        goalNamePlaceholderIndex = 9,
    ),
    scope = scope,
    logger = logger,
) {
    init {
        vmScope.launch {
            var currentIndex = 0
            while (true) {
                stateFlow.update {
                    it.copy(
                        goalNamePlaceholderIndex = currentIndex,
                    )
                }

                currentIndex = currentIndex.incrementIndex(9)
                delay(2000)
            }
        }
    }

    override fun GoalNameScreenAction.process() {
        when (val action = this@process) {
            is GoalNameScreenAction.UpdateGoalName -> {
                stateFlow.update { state ->
                    state.copy(
                        goalName = action.newValue,
                    )
                }
            }
        }
    }
}
