package domain.util

import domain.model.Goal
import domain.model.GoalState
import org.jh.coach.data.local.database.GoalDto

fun GoalDto.toGoal(): Goal =
    Goal(
        id = this.id,
        name = this.name,
        frequency = this.frequency.toInt(),
        state = this.state.toGoalState(),
    )

private fun Long.toGoalState(): GoalState =
    when (this) {
        0L -> GoalState.ACTIVE
        else -> GoalState.DONE
    }
