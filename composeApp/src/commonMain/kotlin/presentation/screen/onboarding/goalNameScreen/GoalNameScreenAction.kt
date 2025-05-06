package presentation.screen.onboarding.goalNameScreen

open class GoalNameScreenAction {
    data class UpdateGoalName(
        val newValue: String,
    ) : GoalNameScreenAction()
}
