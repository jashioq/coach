package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.ONBOARDING_FINISHED_KEY
import domain.repository.DataStoreRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SetOnboardingFinishedUseCaseTest {
    private val dataStoreRepository = mock<DataStoreRepository>()

    private lateinit var cut: SetOnboardingFinishedUseCase

    @BeforeTest
    fun beforeTest() {
        cut = SetOnboardingFinishedUseCase(
            dataStoreRepository = dataStoreRepository
        )
    }

    @Test
    fun `calls datastore with correct parameters`() {
        // GIVEN datastore responds with success
        everySuspend {
            dataStoreRepository.putBooleanPreference(
                key = ONBOARDING_FINISHED_KEY,
                value = any<Boolean>()
            )
        } returns Result.success(Unit)

        // WHEN use case is called with true
        runBlocking {
            val result = cut.call(true)

            // THEN calls datastore once with true
            verifySuspend {
                dataStoreRepository.putBooleanPreference(
                    key = ONBOARDING_FINISHED_KEY,
                    value = true
                )
                assertEquals(Result.success(Unit), result)
            }
        }

        // WHEN use case is called with false
        runBlocking {
            val result = cut.call(false)

            // THEN calls datastore once with false
            verifySuspend {
                dataStoreRepository.putBooleanPreference(
                    key = ONBOARDING_FINISHED_KEY,
                    value = false
                )
                assertEquals(Result.success(Unit), result)
            }
        }
        verifyNoMoreCalls(dataStoreRepository)
    }
}