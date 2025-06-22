package data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import domain.model.Goal
import domain.model.GoalState
import domain.repository.DataBaseRepository
import domain.util.formatLocalDateTimeList
import domain.util.toGoal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import org.jh.coach.data.local.database.Database

class DataBaseRepository(
    private val database: Database,
) : DataBaseRepository {
    override suspend fun fetchAllGoals(): Result<Flow<List<Goal>>> = runCatching {
        database.databaseQueries.selectAllGoals()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map { goalDto ->
                    goalDto.toGoal()
                }
            }
    }

    override suspend fun fetchGoalById(id: Long): Result<Flow<Goal>> = runCatching {
        database.databaseQueries.selectGoalById(id)
            .asFlow()
            .mapToOne(Dispatchers.IO)
            .map { goalDto ->
                goalDto.toGoal()
            }
    }

    override suspend fun addGoal(
        name: String,
        frequency: Int,
    ): Result<Unit> = runCatching {
        database.databaseQueries.insertGoal(
            name = name,
            frequency = frequency.toLong(),
            state = GoalState.ACTIVE.ordinal.toLong(),
            completions = "",
        )
    }

    override suspend fun editGoal(
        id: Long,
        name: String,
        frequency: Int,
        state: GoalState,
        completions: List<LocalDateTime>,
    ): Result<Unit> = runCatching {
        database.databaseQueries.updateGoal(
            id = id,
            name = name,
            frequency = frequency.toLong(),
            state = state.ordinal.toLong(),
            completions = formatLocalDateTimeList(completions),
        )
    }

    override suspend fun deleteGoal(id: Long): Result<Unit> = runCatching {
        database.databaseQueries.deleteGoal(id)
    }
}
