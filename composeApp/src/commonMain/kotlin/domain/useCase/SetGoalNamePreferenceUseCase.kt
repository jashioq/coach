package domain.useCase

import domain.model.GOAL_NAME_KEY
import domain.repository.DataStoreRepository

class SetGoalNamePreferenceUseCase(
    private val dataStoreRepository: DataStoreRepository,
    ) {
    suspend fun call(value: String): Result<Unit> =
        dataStoreRepository.putStringPreference(
            key = GOAL_NAME_KEY,
            value = value,
        )
}