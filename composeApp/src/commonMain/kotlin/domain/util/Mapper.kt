package domain.util

import domain.model.Goal
import org.jh.coach.data.local.database.GoalDto

fun GoalDto.toGoal(): Goal =
    Goal(
        id = this.id,
        name = this.name,
        frequency = this.frequency.toInt(),
    )
