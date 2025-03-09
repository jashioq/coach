package domain.useCase

import domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import org.jh.coach.data.local.database.PostDto

class EmitAllPostsUseCase(
    private val dataBaseRepository: DataBaseRepository,
) {
    suspend fun call(): Result<Flow<List<PostDto>>> =
        dataBaseRepository.fetchAllPosts()
}
