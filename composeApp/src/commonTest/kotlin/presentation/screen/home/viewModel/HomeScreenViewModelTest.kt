package presentation.screen.home.viewModel

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.Goal
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.EmitUserNamePreferenceUseCase
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import util.ViewModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeScreenViewModelTest : ViewModelTest() {
    private val emitUserNamePreferenceUseCase = mock<EmitUserNamePreferenceUseCase>()
    private val emitAllGoalsUseCase = mock<EmitAllGoalsUseCase>()

    private lateinit var viewModel: HomeScreenViewModel

    @Test
    fun `updates userName in state on init`() =
        runTest {
            // GIVEN initial state and emitUserNamePreferenceUseCase responds with success
            val testName = "Test User"
            everySuspend { emitUserNamePreferenceUseCase.call(Unit) } returns Result.success(
                flowOf(
                    testName,
                ),
            )
            everySuspend { emitAllGoalsUseCase.call(Unit) } returns Result.success(emptyFlow())
            viewModel = HomeScreenViewModel(emitUserNamePreferenceUseCase, emitAllGoalsUseCase)

            // THEN calls emitUserNamePreferenceUseCase once and userName is updated in state
            assertEquals(testName, viewModel.state.value.userName)
            verifySuspend {
                emitUserNamePreferenceUseCase.call(Unit)
            }
            verifyNoMoreCalls(emitUserNamePreferenceUseCase)
        }

    @Test
    fun `updates goalId goalName and goalFrequency in state on init`() =
        runTest {
            // GIVEN initial state and emitAllGoalsUseCase responds with success
            val testGoal = Goal(id = 1, name = "Test Goal", frequency = 7)
            everySuspend { emitUserNamePreferenceUseCase.call(Unit) } returns Result.success(
                emptyFlow(),
            )
            everySuspend { emitAllGoalsUseCase.call(Unit) } returns Result.success(
                flowOf(
                    listOf(
                        testGoal,
                    ),
                ),
            )
            viewModel = HomeScreenViewModel(emitUserNamePreferenceUseCase, emitAllGoalsUseCase)

            // THEN calls emitAllGoalsUseCase once and goalId, goalName and goalFrequency are updated in state
            assertEquals(testGoal.id.toString(), viewModel.state.value.goalId)
            assertEquals(testGoal.name, viewModel.state.value.goalName)
            assertEquals(testGoal.frequency.toString(), viewModel.state.value.goalFrequency)
            verifySuspend {
                emitAllGoalsUseCase.call(Unit)
            }
            verifyNoMoreCalls(emitAllGoalsUseCase)
        }
}
