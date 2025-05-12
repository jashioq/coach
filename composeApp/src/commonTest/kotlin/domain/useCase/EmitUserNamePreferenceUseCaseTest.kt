package domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.USER_NAME_KEY
import domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EmitUserNamePreferenceUseCaseTest {
    private val dataStoreRepository = mock<DataStoreRepository>()

    private lateinit var cut: EmitUserNamePreferenceUseCase

    @BeforeTest
    fun beforeTest() {
        cut = EmitUserNamePreferenceUseCase(
            dataStoreRepository = dataStoreRepository,
        )
    }

    @Test
    fun `calls datastore with correct parameters and returns correct result`() {
        // GIVEN datastore responds with success
        val testFlow = flowOf("test")
        everySuspend {
            dataStoreRepository.emitStringPreference(
                key = USER_NAME_KEY,
                default = any<String>(),
            )
        } returns Result.success(testFlow)

        runBlocking {
            // WHEN use case is called
            val result = cut.call(Unit)

            // THEN calls the datastore once with correct parameters and returns correct result
            verifySuspend {
                dataStoreRepository.emitStringPreference(
                    key = USER_NAME_KEY,
                    default = "",
                )
                assertEquals(Result.success(testFlow), result)
            }
        }
        verifyNoMoreCalls(dataStoreRepository)
    }
}
