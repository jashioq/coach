package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow

/**
 * Use case used for emitting a list of all goals from the database as a [Flow].
 * @param dataBaseRepository a [DataBaseRepository] instance.
 */
open class EmitAllGoalsUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Unit, Flow<List<Goal>>> {
    /**
     * @return a [Flow] of [List] of [Goal] representing all goals in the database.
     */
    override suspend fun call(value: Unit): Result<Flow<List<Goal>>> =
        dataBaseRepository.fetchAllGoals()
}
