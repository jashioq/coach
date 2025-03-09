package domain.repository

import kotlinx.coroutines.flow.Flow
import org.jh.coach.data.local.database.PostDto

interface DataBaseRepository {
    suspend fun fetchAllPosts(): Result<Flow<List<PostDto>>>
}
