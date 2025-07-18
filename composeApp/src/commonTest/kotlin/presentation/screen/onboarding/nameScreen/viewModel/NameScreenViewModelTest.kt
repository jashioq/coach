package presentation.screen.onboarding.nameScreen.viewModel

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.useCase.SetUserNamePreferenceUseCase
import kotlinx.coroutines.test.runTest
import presentation.screen.onboarding.nameScreen.NameScreenAction
import util.ViewModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NameScreenViewModelTest : ViewModelTest() {
    private val setUserNamePreferenceUseCase = mock<SetUserNamePreferenceUseCase>()

    private lateinit var viewModel: NameScreenViewModel
    private val testName = "Test User"

    override fun prepare() {
        viewModel = NameScreenViewModel(
            setUserNamePreferenceUseCase = setUserNamePreferenceUseCase,
            logger = logger,
        )
    }

    @Test
    fun `updates userName in state`() =
        runTest {
            // WHEN action UpdateName is called with testName
            viewModel.sendAction(NameScreenAction.UpdateName(testName))

            // THEN name in state is updated
            assertEquals(testName, viewModel.state.value.name)
        }

    @Test
    fun `calls setUserNamePreferenceUseCase with name from state`() =
        runTest {
            // GIVEN setUserNamePreferenceUseCase responds with success and state.name is testName
            everySuspend {
                setUserNamePreferenceUseCase.call(testName)
            } returns Result.success(Unit)
            viewModel.sendAction(NameScreenAction.UpdateName(testName))

            // WHEN action SaveName called
            viewModel.sendAction(NameScreenAction.SaveName)

            // THEN setUserNamePreferenceUseCase is called once with testName
            verifySuspend {
                setUserNamePreferenceUseCase.call(testName)
            }
            verifyNoMoreCalls(setUserNamePreferenceUseCase)
        }
}
