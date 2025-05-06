package domain.useCase

import domain.model.ONBOARDING_FINISHED_KEY
import domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class EmitOnboardingFinishedUseCase(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun call(): Result<Flow<Boolean>> =
        dataStoreRepository.emitBooleanPreference(
            key = ONBOARDING_FINISHED_KEY,
            default = false,
        )
}
