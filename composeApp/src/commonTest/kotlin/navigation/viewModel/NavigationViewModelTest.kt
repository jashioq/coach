package navigation.viewModel

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import domain.model.OnboardingState
import domain.useCase.EmitOnboardingFinishedUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
            everySuspend {
                emitOnboardingFinishedUseCase.call(Unit)
            } returns Result.success(
                flow {
                    emit(true)
                    // Flow completes here automatically
                },
            )

            viewModel = NavigationViewModel(
                emitOnboardingFinishedUseCase = emitOnboardingFinishedUseCase,
                scope = backgroundScope,
            )

            runCurrent()

            // Add this to see what state you actually have
            println("Current state: ${viewModel.state.value}")
            assertEquals(OnboardingState.FINISHED, viewModel.state.value)
        }

    @Test
    fun `updates state to NOT_FINISHED when emitOnboardingFinishedUseCase emits false`() =
        runTest {
            // GIVEN emitOnboardingFinishedUseCase returns false
            everySuspend {
                emitOnboardingFinishedUseCase.call(Unit)
            } returns Result.success(flowOf(false))

            // WHEN ViewModel is created
            viewModel = NavigationViewModel(
                emitOnboardingFinishedUseCase = emitOnboardingFinishedUseCase,
                scope = backgroundScope,
            )

            runCurrent()

            // THEN state is updated to NOT_FINISHED
            assertEquals(OnboardingState.NOT_FINISHED, viewModel.state.value)
        }
}
