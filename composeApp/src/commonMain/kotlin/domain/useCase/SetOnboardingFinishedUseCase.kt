package domain.useCase

import domain.model.ONBOARDING_FINISHED_KEY
import domain.repository.DataStoreRepository

class SetOnboardingFinishedUseCase(
    private val dataStoreRepository: DataStoreRepository,
) {
    suspend fun call(value: Boolean): Result<Unit> =
        dataStoreRepository.putBooleanPreference(
            key = ONBOARDING_FINISHED_KEY,
            value = value,
        )
}
