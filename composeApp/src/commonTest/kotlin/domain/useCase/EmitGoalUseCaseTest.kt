package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.Goal
import domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EmitGoalUseCaseTest {
    private val dataBaseRepository = mock<DataBaseRepository>()

    private lateinit var cut: EmitGoalUseCase

    @BeforeTest
    fun beforeTest() {
        cut = EmitGoalUseCase(
            dataBaseRepository = dataBaseRepository
        )
    }

    @Test
    fun `calls database with correct parameter and returns correct result`() {
        // GIVEN database repository responds with success
        val testResult = flowOf(
            Goal(
                id = 1,
                name = "testName",
                frequency = 5,
            )
        )
        everySuspend {
            dataBaseRepository.fetchGoalById(any<Long>())
        } returns Result.success(testResult)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(1.toLong())

            // THEN calls database once with correct parameter and returns correct result
            verifySuspend {
                dataBaseRepository.fetchGoalById(1.toLong())
                assertEquals(Result.success(testResult), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }

    @Test
    fun `calls database with correct parameter and returns failure when goal is not found`() {
        // GIVEN database repository responds with failure
        val testExpectation = Exception("Goal not found")
        everySuspend {
            dataBaseRepository.fetchGoalById(any<Long>())
        } returns Result.failure(testExpectation)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(1.toLong())

            // THEN calls database once with correct parameter and returns failure
            verifySuspend {
                dataBaseRepository.fetchGoalById(1.toLong())
                assertEquals(Result.failure(testExpectation), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }
}