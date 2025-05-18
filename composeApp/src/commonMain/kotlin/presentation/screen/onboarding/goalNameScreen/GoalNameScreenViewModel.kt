package presentation.screen.onboarding.goalNameScreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.util.CoreViewModel

class GoalNameScreenViewModel : CoreViewModel<GoalNameScreenState, GoalNameScreenAction>(
    initialState = GoalNameScreenState(
        goalName = "",
        goalNamePlaceholderIndex = 9,
    ),
) {
    init {
        viewModelScope.launch {
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

    private fun Int.incrementIndex(maxIndex: Int): Int =
        if (this >= maxIndex) {
            0
        } else {
            this + 1
        }
}
