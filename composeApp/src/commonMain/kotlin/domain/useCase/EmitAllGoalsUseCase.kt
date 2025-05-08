package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow

class EmitAllGoalsUseCase(
    private val dataBaseRepository: DataBaseRepository,
): UseCase<Unit, Flow<List<Goal>>> {
    override suspend fun call(value: Unit): Result<Flow<List<Goal>>> =
        dataBaseRepository.fetchAllGoals()
}
