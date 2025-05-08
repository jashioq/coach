package domain.useCase

import domain.repository.DataBaseRepository
import domain.util.UseCase

class DeleteGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<String, Unit> {
    override suspend fun call(value: String): Result<Unit> =
        dataBaseRepository.deleteGoal(value)
}
