package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.Goal
import domain.repository.DataBaseRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EditGoalUseCaseTest {
    private val dataBaseRepository = mock<DataBaseRepository>()

    private lateinit var cut: EditGoalUseCase

    @BeforeTest
    fun beforeTest() {
        cut = EditGoalUseCase(
            dataBaseRepository = dataBaseRepository,
        )
    }

    @Test
    fun `calls database with correct parameters`() {
        // GIVEN database repository responds with success
        val testGoal = Goal(
            id = 1,
            name = "testName",
            frequency = 5,
        )
        everySuspend {
            dataBaseRepository.editGoal(any<Long>(), any<String>(), any<Int>())
        } returns Result.success(Unit)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(testGoal)

            // THEN calls database once with correct parameters
            verifySuspend {
                dataBaseRepository.editGoal(testGoal.id, testGoal.name, testGoal.frequency)
                assertEquals(Result.success(Unit), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }
}
