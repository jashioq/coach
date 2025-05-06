package domain.useCase

import domain.repository.DataBaseRepository

class AddGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) {
    suspend fun call(name: String, frequency: Int): Result<Unit> =
        dataBaseRepository.addGoal(
            name = name,
            frequency = frequency,
        )
}
