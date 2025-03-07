package domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun putIntPreference(
        key: String,
        value: Int,
    ): Unit

    suspend fun emitIntPreference(
        key: String,
        default: Int,
    ): Flow<Int>
}
