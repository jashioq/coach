package presentation.screen.onboarding.goalNameScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.useCase.SetGoalNamePreferenceUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GoalNameScreenViewModel(
    private val setGoalNamePreferenceUseCase: SetGoalNamePreferenceUseCase,
) : ViewModel() {
    private val _goalName = MutableStateFlow("")
    val goalName = _goalName.asStateFlow()

    private val _goalNamePlaceholder = MutableStateFlow("Run")
    val goalNamePlaceholder = _goalNamePlaceholder.asStateFlow()

    private var currentIndex = 0

    private val goalNamePlaceholderList =
        listOf(
            "Exercise",
            "Write",
            "Meditate",
            "Practice",
            "Learn",
            "Create",
            "Focus",
            "Paint",
            "Code",
            "Run"
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

            GoalNameScreenAction.SaveGoalName -> {
                viewModelScope.launch {
                    setGoalNamePreferenceUseCase.call(goalName.value)
                }
            }
        }
    }

    private fun Int.incrementIndex(): Int {
        val maxIndex = goalNamePlaceholderList.size - 1

        return if (this >= maxIndex) 0
        else this + 1
    }
}