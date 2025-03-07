package domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun putIntPreference(
        key: String,
        value: Int,
    ): Result<Unit>

    suspend fun emitIntPreference(
        key: String,
        default: Int,
    ): Result<Flow<Int>>
}
