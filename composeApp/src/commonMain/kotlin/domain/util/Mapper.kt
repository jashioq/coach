package domain.util

import domain.model.Goal
import org.jh.coach.data.local.database.GoalDto

fun GoalDto.toGoal(): Goal =
    Goal(
        name = this.name,
        title = this.title,
        reminders = this.reminders?.splitToSequence(',')?.toList()
    )
