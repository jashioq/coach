package domain.repository

import domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {
    suspend fun fetchAllGoals(): Result<Flow<List<Goal>>>

    suspend fun fetchGoalById(id: String): Result<Flow<Goal>>

    suspend fun addGoal(name: String, frequency: Int): Result<Unit>

    suspend fun editGoal(id: String, name: String, frequency: Int): Result<Unit>

    suspend fun deleteGoal(id: String): Result<Unit>
}
