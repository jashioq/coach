package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow

class EmitGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) {
    suspend fun call(id: String): Result<Flow<Goal>> =
        dataBaseRepository.fetchGoalById(id)
}