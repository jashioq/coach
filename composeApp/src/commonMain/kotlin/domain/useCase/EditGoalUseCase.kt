package domain.useCase

import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.UseCase

/**
 * Use case used for editing a goal in the database.
 * @param dataBaseRepository a [DataBaseRepository] instance.
 */
open class EditGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Goal, Unit> {
    /**
     * @param value a [Goal] representing the goal to edit.
     */
    override suspend fun call(value: Goal): Result<Unit> =
        dataBaseRepository.editGoal(
            id = value.id,
            name = value.name,
            frequency = value.frequency,
        )
}
