package domain.useCase

import domain.repository.DataBaseRepository
import domain.util.UseCase

/**
 * Use case used for adding a goal to the database.
 * @param dataBaseRepository a [DataBaseRepository] instance.
 */
open class AddGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Pair<String, Int>, Unit> {
    /**
     * @param value a [Pair] of [String] and [Int] representing goal name and frequency.
     */
    override suspend fun call(value: Pair<String, Int>): Result<Unit> =
        dataBaseRepository.addGoal(
            name = value.first,
            frequency = value.second,
        )
}
