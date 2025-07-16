package domain.repository

import domain.model.Goal
import domain.model.GoalState
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

interface DataBaseRepository {
    /**
     * Emit list of all goals from the database as a [Flow].
     */
    suspend fun fetchAllGoals(): Result<Flow<List<Goal>>>

    /**
     * Emit goal by id from the database as a [Flow].
     * @param id a [Long] representing goal id.
     */
    suspend fun fetchGoalById(id: Long): Result<Flow<Goal>>

    /**
     * Write goal to the database.
     * @param name a [String] representing goal name.
     * @param frequency an [Int] representing goal frequency.
     */
    suspend fun addGoal(name: String, frequency: Int): Result<Unit>

    /**
     * Edit goal in the database.
     * @param id a [Long] representing id of the goal to edit.
     * @param name a [String] representing new goal name.
     * @param frequency an [Int] representing new goal frequency.
     * @param state a [GoalState] representing new goal state.
     * @param completions a [List] of [LocalDateTime] representing new goal completions.
     */
    suspend fun editGoal(
        id: Long,
        name: String,
        frequency: Int,
        state: GoalState,
        completions: List<LocalDateTime>,
    ): Result<Unit>

    /**
     * Delete goal from the database.
     * @param id a [Long] representing id of the goal to delete.
     */
    suspend fun deleteGoal(id: Long): Result<Unit>
}
