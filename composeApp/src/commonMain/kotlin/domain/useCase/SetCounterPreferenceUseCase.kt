package domain.useCase

import domain.model.COUNTER_KEY
import domain.repository.DataStoreRepository

class SetCounterPreferenceUseCase(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun call(value: Int): Result<Unit> =
        dataStoreRepository.putIntPreference(
            key = COUNTER_KEY,
            value = value,
        )
}
