package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow

class EmitAllGoalsUseCase(
    private val dataBaseRepository: DataBaseRepository,
) {
    suspend fun call(): Result<Flow<List<Goal>>> =
        dataBaseRepository.fetchAllGoals()
}
