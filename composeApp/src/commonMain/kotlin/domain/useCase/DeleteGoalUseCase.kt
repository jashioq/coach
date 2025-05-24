package domain.useCase

import domain.repository.DataBaseRepository
import domain.util.UseCase

open class DeleteGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Long, Unit> {
    override suspend fun call(value: Long): Result<Unit> =
        dataBaseRepository.deleteGoal(value)
}
