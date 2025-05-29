package domain.useCase

import domain.model.ONBOARDING_FINISHED_KEY
import domain.repository.DataStoreRepository
import domain.util.UseCase

/**
 * Use case used for setting onboarding finished status in the datastore.
 * @param dataStoreRepository a [DataStoreRepository] instance.
 */
open class SetOnboardingFinishedUseCase(
    private val dataStoreRepository: DataStoreRepository,
) : UseCase<Boolean, Unit> {
    /**
     * @param value a [Boolean] representing onboarding finished status.
     */
    override suspend fun call(value: Boolean): Result<Unit> =
        dataStoreRepository.putBooleanPreference(
            key = ONBOARDING_FINISHED_KEY,
            value = value,
        )
}
