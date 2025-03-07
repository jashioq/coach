package domain.useCase

import domain.model.COUNTER_KEY
import domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class EmitCounterPreferenceUseCase(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun call(): Flow<Int> =
        dataStoreRepository.emitIntPreference(
            key = COUNTER_KEY,
            default = 0,
        )
}
