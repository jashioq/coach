package presentation.screen.home

import domain.model.GoalState

open class HomeScreenAction {
    data class UpdateGoalState(
        val id: Long,
        val newState: GoalState,
    ) : HomeScreenAction()
}
