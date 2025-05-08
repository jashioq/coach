package domain.util

interface UseCase<in Val, out Res> {
    suspend fun call(value: Val): Result<Res>
}