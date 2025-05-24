package domain.useCase

import domain.model.ONBOARDING_FINISHED_KEY
import domain.repository.DataStoreRepository
import domain.util.UseCase

open class SetOnboardingFinishedUseCase(
    private val dataStoreRepository: DataStoreRepository,
) : UseCase<Boolean, Unit> {
    override suspend fun call(value: Boolean): Result<Unit> =
        dataStoreRepository.putBooleanPreference(
            key = ONBOARDING_FINISHED_KEY,
            value = value,
        )
}
