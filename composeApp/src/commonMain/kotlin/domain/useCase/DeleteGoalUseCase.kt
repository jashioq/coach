package domain.useCase

import domain.repository.DataBaseRepository

class DeleteGoalUseCase (
    private val dataBaseRepository: DataBaseRepository,
) {
    suspend fun call(id: String): Result<Unit> =
        dataBaseRepository.deleteGoal(id)
}