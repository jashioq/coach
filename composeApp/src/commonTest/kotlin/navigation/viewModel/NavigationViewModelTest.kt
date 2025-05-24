package navigation.viewModel

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import domain.model.OnboardingState
import domain.useCase.EmitOnboardingFinishedUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import util.ViewModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NavigationViewModelTest : ViewModelTest() {
    private val emitOnboardingFinishedUseCase = mock<EmitOnboardingFinishedUseCase>()

    private lateinit var viewModel: NavigationViewModel

    @Test
    fun `updates state to FINISHED when emitOnboardingFinishedUseCase emits true`() =
        runTest {
            // GIVEN initial state and emitOnboardingFinishedUseCase returns true
            viewModel = NavigationViewModel(
                emitOnboardingFinishedUseCase = emitOnboardingFinishedUseCase,
                scope = this,
            )
            everySuspend {
                emitOnboardingFinishedUseCase.call(Unit)
            } returns Result.success(flowOf(true))

            // WHEN emitOnboardingFinishedUseCase is collected
            runCurrent()

            // THEN state is updated to FINISHED
            assertEquals(OnboardingState.FINISHED, viewModel.state.value)
        }

    @Test
    fun `updates state to NOT_FINISHED when emitOnboardingFinishedUseCase emits false`() =
        runTest {
            // GIVEN initial state and emitOnboardingFinishedUseCase returns false
            viewModel = NavigationViewModel(
                emitOnboardingFinishedUseCase = emitOnboardingFinishedUseCase,
                scope = this,
            )
            everySuspend {
                emitOnboardingFinishedUseCase.call(Unit)
            } returns Result.success(flowOf(false))

            // WHEN emitOnboardingFinishedUseCase is collected
            runCurrent()

            // THEN state is updated to FINISHED
            assertEquals(OnboardingState.NOT_FINISHED, viewModel.state.value)
        }
}
