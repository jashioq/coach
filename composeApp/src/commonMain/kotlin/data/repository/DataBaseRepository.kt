package data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import domain.model.Goal
import domain.repository.DataBaseRepository
import domain.util.toGoal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun fetchGoalById(id: String): Result<Flow<Goal>> = runCatching {
        database.databaseQueries.selectGoalById(id.toLong())
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
        )
    }

    override suspend fun editGoal(
        id: String,
        name: String,
        frequency: Int,
    ): Result<Unit> = runCatching {
        database.databaseQueries.updateGoal(
            id = id.toLong(),
            name = name,
            frequency = frequency.toLong(),
        )
    }

    override suspend fun deleteGoal(id: String): Result<Unit> = runCatching {
        database.databaseQueries.deleteGoal(id.toLong())
    }
}
