package domain.useCase

import domain.model.USER_NAME_KEY
import domain.repository.DataStoreRepository
import domain.util.UseCase
import kotlinx.coroutines.flow.Flow

/**
 * Use case used for emitting user name from the datastore as a [Flow].
 * @param dataStoreRepository a [DataStoreRepository] instance.
 */
open class EmitUserNamePreferenceUseCase(
    private val dataStoreRepository: DataStoreRepository,
) : UseCase<Unit, Flow<String>> {
    /**
     * @return a [Flow] of [String] representing user name.
     */
    override suspend fun call(value: Unit): Result<Flow<String>> =
        dataStoreRepository.emitStringPreference(
            key = USER_NAME_KEY,
            default = "",
        )
}
