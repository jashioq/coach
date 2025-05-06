package presentation.screen.onboarding.goalNameScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GoalNameScreenViewModel : ViewModel() {
    private val _goalName = MutableStateFlow("")
    val goalName = _goalName.asStateFlow()

    private val _goalNamePlaceholder = MutableStateFlow("run")
    val goalNamePlaceholder = _goalNamePlaceholder.asStateFlow()

    private var currentIndex = 0

    private val goalNamePlaceholderList =
        listOf(
            "exercise",
            "write",
            "meditate",
            "practice",
            "learn",
            "create",
            "focus",
            "paint",
            "code",
            "run",
        )

    init {
        viewModelScope.launch {
            while (true) {
                _goalNamePlaceholder.value = goalNamePlaceholderList[currentIndex]
                currentIndex = currentIndex.incrementIndex()
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

    private fun Int.incrementIndex(): Int {
        val maxIndex = goalNamePlaceholderList.size - 1

        return if (this >= maxIndex) {
            0
        } else {
            this + 1
        }
    }
}
