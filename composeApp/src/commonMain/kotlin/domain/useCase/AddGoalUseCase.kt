package domain.useCase

import domain.repository.DataBaseRepository
import domain.util.UseCase

class AddGoalUseCase(
    private val dataBaseRepository: DataBaseRepository,
) : UseCase<Pair<String, Int>, Unit> {
    override suspend fun call(value: Pair<String, Int>): Result<Unit> =
        dataBaseRepository.addGoal(
            name = value.first,
            frequency = value.second,
        )
}
