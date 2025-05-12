package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase

class EditGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Goal, Unit> {
    override suspend fun call(value: Goal): Result<Unit> =
        dataBaseRepository.editGoal(
            id = value.id,
            name = value.name,
            frequency = value.frequency,
        )
}
