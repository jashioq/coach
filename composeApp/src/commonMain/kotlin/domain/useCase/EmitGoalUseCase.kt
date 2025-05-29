package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow

/**
 * Use case used for emitting a goal from the database as a [Flow].
 * @param dataBaseRepository a [DataBaseRepository] instance.
 */
open class EmitGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Long, Flow<Goal>> {
    /**
     * @param value a [Long] representing id of the goal to emit.
     * @return a [Flow] of [Goal] representing the goal with given id.
     */
    override suspend fun call(value: Long): Result<Flow<Goal>> =
        dataBaseRepository.fetchGoalById(value)
}
