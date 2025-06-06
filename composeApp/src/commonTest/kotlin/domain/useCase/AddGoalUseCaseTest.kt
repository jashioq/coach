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

class AddGoalUseCaseTest {
    private val dataBaseRepository = mock<DataBaseRepository>()

    private lateinit var useCase: AddGoalUseCase

    @BeforeTest
    fun beforeTest() {
        useCase = AddGoalUseCase(
            dataBaseRepository = dataBaseRepository,
        )
    }

    @Test
    fun `calls database with correct parameters`() {
        // GIVEN database repository responds with success
        everySuspend {
            dataBaseRepository.addGoal(any<String>(), any<Int>())
        } returns Result.success(Unit)

        runTest {
            // WHEN use case is called
            val result = useCase.call("testName" to 5)

            // THEN calls database once with correct parameters
            verifySuspend {
                dataBaseRepository.addGoal("testName", 5)
                assertEquals(Result.success(Unit), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }
}
