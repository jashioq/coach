package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.repository.DataBaseRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DeleteGoalUseCaseTest {
    private val dataBaseRepository = mock<DataBaseRepository>()

    private lateinit var useCase: DeleteGoalUseCase

    @BeforeTest
    fun beforeTest() {
        useCase = DeleteGoalUseCase(
            dataBaseRepository = dataBaseRepository,
        )
    }

    @Test
    fun `calls database with correct parameter`() {
        // GIVEN database repository responds with success
        everySuspend {
            dataBaseRepository.deleteGoal(any<Long>())
        } returns Result.success(Unit)

        runTest {
            // WHEN use case is called
            val result = useCase.call(1.toLong())

            // THEN calls database once with correct parameters
            verifySuspend {
                dataBaseRepository.deleteGoal(1.toLong())
                assertEquals(Result.success(Unit), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }
}
