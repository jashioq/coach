package domain.useCase

import domain.model.USER_NAME_KEY
import domain.repository.DataStoreRepository

class SetNamePreferenceUseCase(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun call(value: String): Result<Unit> =
        dataStoreRepository.putStringPreference(
            key = USER_NAME_KEY,
            value = value,
        )
}
