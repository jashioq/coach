package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository

class AddGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) {
    suspend fun call(goal: Goal): Result<Unit> =
        dataBaseRepository.addGoal(
            name = goal.name,
            title = goal.title,
            reminders = goal.reminders,
        )
}