package presentation.screen.onboarding.goalNameScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenAction

class GoalNameScreenViewModel : ViewModel() {
    private val _goalName = MutableStateFlow("")
    val goalName = _goalName.asStateFlow()

    private val _goalNamePlaceholderIndex = MutableStateFlow(9)
    val goalNamePlaceholderIndex = _goalNamePlaceholderIndex.asStateFlow()

    private var currentIndex = 0

    init {
        viewModelScope.launch {
            while (true) {
                _goalNamePlaceholderIndex.value = currentIndex
                currentIndex = currentIndex.incrementIndex(9)
                delay(2000)
            }
        }
    }

    fun action(action: GoalNameScreenAction) {
        when (action) {
            is GoalNameScreenAction.UpdateGoalName -> {
                _goalName.value = action.newValue
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
