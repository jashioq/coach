package domain.useCase

import domain.model.ONBOARDING_FINISHED_KEY
import domain.repository.DataStoreRepository
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow

class EmitOnboardingFinishedUseCase(
    private val dataStoreRepository: DataStoreRepository,
) : UseCase<Unit, Flow<Boolean>> {
    override suspend fun call(value: Unit): Result<Flow<Boolean>> =
        dataStoreRepository.emitBooleanPreference(
            key = ONBOARDING_FINISHED_KEY,
            default = false,
        )
}
