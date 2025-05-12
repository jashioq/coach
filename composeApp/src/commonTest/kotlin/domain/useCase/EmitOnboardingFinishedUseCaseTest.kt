package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.ONBOARDING_FINISHED_KEY
import domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EmitOnboardingFinishedUseCaseTest {
    private val dataStoreRepository = mock<DataStoreRepository>()

    private lateinit var cut: EmitOnboardingFinishedUseCase

    @BeforeTest
    fun beforeTest() {
        cut = EmitOnboardingFinishedUseCase(
            dataStoreRepository = dataStoreRepository,
        )
    }

    @Test
    fun `calls datastore with correct parameters and returns true`() {
        // GIVEN datastore responds with success true
        val testFlow = flowOf(true)
        everySuspend {
            dataStoreRepository.emitBooleanPreference(
                key = ONBOARDING_FINISHED_KEY,
                default = any<Boolean>(),
            )
        } returns Result.success(testFlow)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(Unit)

            // THEN calls the datastore once with correct parameters and returns true
            verifySuspend {
                dataStoreRepository.emitBooleanPreference(
                    key = ONBOARDING_FINISHED_KEY,
                    default = false,
                )
            }
            assertEquals(Result.success(testFlow), result)
        }
        verifyNoMoreCalls(dataStoreRepository)
    }

    @Test
    fun `calls datastore with correct parameters and returns false`() {
        // GIVEN datastore responds with success false
        val testFlow = flowOf(false)
        everySuspend {
            dataStoreRepository.emitBooleanPreference(
                key = ONBOARDING_FINISHED_KEY,
                default = any<Boolean>(),
            )
        } returns Result.success(testFlow)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(Unit)

            // THEN calls the datastore once with correct parameters and returns false
            verifySuspend {
                dataStoreRepository.emitBooleanPreference(
                    key = ONBOARDING_FINISHED_KEY,
                    default = false,
                )
            }
            assertEquals(Result.success(testFlow), result)
        }
        verifyNoMoreCalls(dataStoreRepository)
    }
}
