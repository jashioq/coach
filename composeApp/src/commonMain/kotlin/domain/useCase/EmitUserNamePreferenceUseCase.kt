package domain.useCase

import domain.model.USER_NAME_KEY
import domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class EmitUserNamePreferenceUseCase(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun call(): Result<Flow<String>> =
        dataStoreRepository.emitStringPreference(
            key = USER_NAME_KEY,
            default = "",
        )
}
