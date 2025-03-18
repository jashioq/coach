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

    suspend fun putStringPreference(
        key: String,
        value: String,
    ): Result<Unit>

    suspend fun emitStringPreference(
        key: String,
        default: String,
    ): Result<Flow<String>>

    suspend fun putBooleanPreference(
        key: String,
        value: Boolean,
    ): Result<Unit>

    suspend fun emitBooleanPreference(
        key: String,
        default: Boolean,
    ): Result<Flow<Boolean>>

    suspend fun putFloatPreference(
        key: String,
        value: Float,
    ): Result<Unit>

    suspend fun emitFloatPreference(
        key: String,
        default: Float,
    ): Result<Flow<Float>>
}
