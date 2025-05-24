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
                _state.update {
                    it.copy(
                        goalNamePlaceholderIndex = currentIndex,
                    )
                }

                currentIndex = currentIndex.incrementIndex(9)
                delay(2000)
            }
        }
    }

    fun dispatch(action: GoalNameScreenAction) =
        action.process {
            when (it) {
                is GoalNameScreenAction.UpdateGoalName -> {
                    _state.update { state ->
                        state.copy(
                            goalName = it.newValue,
                        )
                    }
                }
            }
        }
}
