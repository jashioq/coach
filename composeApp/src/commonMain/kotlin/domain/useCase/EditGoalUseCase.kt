package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase

class EditGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Pair<String, Goal>, Unit> {
    override suspend fun call(value: Pair<String, Goal>): Result<Unit> =
        dataBaseRepository.editGoal(
            id = value.first,
            name = value.second.name,
            frequency = value.second.frequency,
        )
}
