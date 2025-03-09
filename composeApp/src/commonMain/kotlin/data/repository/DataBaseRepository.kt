package data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import domain.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import org.jh.coach.data.local.database.Database
import org.jh.coach.data.local.database.PostDto

class DataBaseRepository(
    private val database: Database,
) : DataBaseRepository {
    override suspend fun fetchAllPosts(): Result<Flow<List<PostDto>>> =
        kotlin.runCatching {
            database.databaseQueries.selectAllPosts()
                .asFlow()
                .mapToList(Dispatchers.IO)
        }
}
