package presentation.screen.home.viewModel

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.model.Goal
import domain.model.GoalState
import domain.useCase.EditGoalUseCase
import domain.useCase.EmitAllGoalsUseCase
import domain.useCase.MonitorGoalStateUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import presentation.screen.home.HomeScreenAction
import util.ViewModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeScreenViewModelTest : ViewModelTest() {
    private val emitAllGoalsUseCase = mock<EmitAllGoalsUseCase>()
    private val editGoalUseCase = mock<EditGoalUseCase>()
    private val monitorGoalStateUseCase = mock<MonitorGoalStateUseCase>()

    private lateinit var viewModel: HomeScreenViewModel

    val testGoals = listOf(
        Goal(
            id = 1,
            name = "Test Goal",
            frequency = 7,
            state = GoalState.ACTIVE,
            completions = emptyList(),
        ),
        Goal(
            id = 2,
            name = "Test Goal2",
            frequency = 5,
            state = GoalState.DONE,
            completions = emptyList(),
        ),
    )

    override fun prepare() {
        everySuspend { emitAllGoalsUseCase.call(Unit) } returns Result.success(
            flowOf(
                testGoals,
            ),
        )
        everySuspend { editGoalUseCase.call(any()) } returns Result.success(Unit)
        everySuspend { monitorGoalStateUseCase.call(any()) } returns Result.success(GoalState.ACTIVE)
        viewModel =
            HomeScreenViewModel(emitAllGoalsUseCase, editGoalUseCase, monitorGoalStateUseCase, logger = logger)
    }

    @Test
    fun `updates goals in state on init`() =
        runTest {
            // THEN goals are updated in state
            assertEquals(testGoals, viewModel.state.value.goals)
            verifySuspend {
                emitAllGoalsUseCase.call(Unit)
            }
            verifyNoMoreCalls(emitAllGoalsUseCase)
        }

    @Test
    fun `calls editGoalUseCase once on UpdateGoalState action`() =
        runTest {
            // GIVEN initial state and emitAllGoalsUseCase responds with success
            val testGoal = testGoals.first()
            val testGoalId = testGoal.id
            val testGoalState = GoalState.DONE

            everySuspend { editGoalUseCase.call(any()) } returns Result.success(Unit)
            everySuspend { monitorGoalStateUseCase.call(any()) } returns Result.success(GoalState.ACTIVE)

            // WHEN action UpdateGoalState is called
            viewModel.sendAction(HomeScreenAction.UpdateGoalState(testGoalId, testGoalState))

            // THEN calls editGoalUseCase with Goal
            verifySuspend {
                editGoalUseCase.call(any<Goal>())
            }
            verifyNoMoreCalls(editGoalUseCase)
        }
}
