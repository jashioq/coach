package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow

open class EmitGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Long, Flow<Goal>> {
    override suspend fun call(value: Long): Result<Flow<Goal>> =
        dataBaseRepository.fetchGoalById(value)
}
