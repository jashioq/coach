package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository

class EditGoalUseCase (
    private val dataBaseRepository: DataBaseRepository,
) {
    suspend fun call(id: String, goal: Goal): Result<Unit> =
        dataBaseRepository.editGoal(
            id = id,
            name = goal.name,
            title = goal.title,
            reminders = goal.reminders,
        )
}