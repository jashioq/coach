package domain.useCase

import domain.repository.DataBaseRepository
import domain.util.UseCase

/**
 * Use case used for deleting a goal from the database.
 * @param dataBaseRepository a [DataBaseRepository] instance.
 */
open class DeleteGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Long, Unit> {
    /**
     * @param value a [Long] representing id of the goal to delete.
     */
    override suspend fun call(value: Long): Result<Unit> =
        dataBaseRepository.deleteGoal(value)
}
