package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow

class EmitGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<String, Flow<Goal>> {
    override suspend fun call(value: String): Result<Flow<Goal>> =
        dataBaseRepository.fetchGoalById(value)
}
