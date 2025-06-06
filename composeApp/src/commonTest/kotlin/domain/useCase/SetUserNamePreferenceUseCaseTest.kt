package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.USER_NAME_KEY
import domain.repository.DataStoreRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SetUserNamePreferenceUseCaseTest {
    private val dataStoreRepository = mock<DataStoreRepository>()

    private lateinit var useCase: SetUserNamePreferenceUseCase

    @BeforeTest
    fun beforeTest() {
        useCase = SetUserNamePreferenceUseCase(
            dataStoreRepository = dataStoreRepository,
        )
    }

    @Test
    fun `calls datastore with correct parameters`() {
        // GIVEN datastore responds with success
        everySuspend {
            dataStoreRepository.putStringPreference(
                key = USER_NAME_KEY,
                value = any<String>(),
            )
        } returns Result.success(Unit)

        // WHEN use case is called
        runTest {
            val result = useCase.call("test")

            // THEN calls datastore once with correct parameters and returns correct result
            verifySuspend {
                dataStoreRepository.putStringPreference(
                    key = USER_NAME_KEY,
                    value = "test",
                )
            }
            assertEquals(Result.success(Unit), result)
        }
        verifyNoMoreCalls(dataStoreRepository)
    }
}
