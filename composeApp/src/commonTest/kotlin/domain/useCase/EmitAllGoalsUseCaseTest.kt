package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
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

class EmitAllGoalsUseCaseTest {
    private val dataBaseRepository = mock<DataBaseRepository>()

    private lateinit var cut: EmitAllGoalsUseCase

    @BeforeTest
    fun beforeTest() {
        cut = EmitAllGoalsUseCase(
            dataBaseRepository = dataBaseRepository
        )
    }

    @Test
    fun `calls database and returns correct result`() {
        // GIVEN database repository responds with success
        val testResult = flowOf(
            listOf(
                Goal(
                    id = 1,
                    name = "testName",
                    frequency = 5,
                ),
                Goal(
                    id = 2,
                    name = "testName2",
                    frequency = 4,
                )
            )
        )
        everySuspend {
            dataBaseRepository.fetchAllGoals()
        } returns Result.success(testResult)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(Unit)

            // THEN calls database once and returns correct result
            verifySuspend {
                dataBaseRepository.fetchAllGoals()
                assertEquals(Result.success(testResult), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }

    @Test
    fun `calls database and returns failure when goals are not found`() {
        // GIVEN database repository responds with failure
        val testExpectation = Exception("Goals not found")
        everySuspend {
            dataBaseRepository.fetchAllGoals()
        } returns Result.failure(testExpectation)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(Unit)

            // THEN calls database once and returns failure
            verifySuspend {
                dataBaseRepository.fetchAllGoals()
                assertEquals(Result.failure(testExpectation), result)
            }
        }
        verifyNoMoreCalls(dataBaseRepository)
    }
}