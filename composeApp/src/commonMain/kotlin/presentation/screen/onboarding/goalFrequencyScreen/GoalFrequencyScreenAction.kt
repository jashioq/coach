package presentation.screen.onboarding.goalFrequencyScreen

open class GoalFrequencyScreenAction {
    data class SaveGoal(
        val name: String,
    ) : GoalFrequencyScreenAction()

    data class UpdateFrequency(
        val newValue: Float,
    ) : GoalFrequencyScreenAction()
}
