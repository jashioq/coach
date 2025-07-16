package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.Goal
import domain.model.GoalState
import domain.repository.DataBaseRepository
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EditGoalUseCaseTest {
    private val dataBaseRepository = mock<DataBaseRepository>()

    private lateinit var useCase: EditGoalUseCase

    @BeforeTest
    fun beforeTest() {
        useCase = EditGoalUseCase(
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
            state = GoalState.ACTIVE,
            completions = emptyList(),
        )
        everySuspend {
            dataBaseRepository.editGoal(
                any<Long>(), any<String>(), any<Int>(),
                any<GoalState>(), any<List<LocalDateTime>>(),
            )
        } returns Result.success(Unit)

        runTest {
            // WHEN use case is called
            val result = useCase.call(testGoal)

            // THEN calls database once with correct parameters
            verifySuspend {
                dataBaseRepository.editGoal(testGoal.id, testGoal.name, testGoal.frequency, testGoal.state, testGoal.completions)
                assertEquals(Result.success(Unit), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }
}
