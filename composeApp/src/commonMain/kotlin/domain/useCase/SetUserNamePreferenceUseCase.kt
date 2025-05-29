package domain.useCase

import domain.model.USER_NAME_KEY
import domain.repository.DataStoreRepository
import domain.util.UseCase

/**
 * Use case used for setting user name in the datastore.
 * @param dataStoreRepository a [DataStoreRepository] instance.
 */
open class SetUserNamePreferenceUseCase(
    private val dataStoreRepository: DataStoreRepository,
) : UseCase<String, Unit> {
    /**
     * @param value a [String] representing user name.
     */
    override suspend fun call(value: String): Result<Unit> =
        dataStoreRepository.putStringPreference(
            key = USER_NAME_KEY,
            value = value,
        )
}
