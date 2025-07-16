package presentation.screen.onboarding.goalFrequencyScreen.viewModel

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import domain.useCase.AddGoalUseCase
import domain.useCase.SetOnboardingFinishedUseCase
import kotlinx.coroutines.test.runTest
import presentation.screen.onboarding.goalFrequencyScreen.GoalFrequencyScreenAction
import util.ViewModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GoalFrequencyScreenViewModelTest : ViewModelTest() {
    private val addGoalUseCase = mock<AddGoalUseCase>()
    private val setOnboardingFinishedUseCase = mock<SetOnboardingFinishedUseCase>()

    private lateinit var viewModel: GoalFrequencyScreenViewModel
    private val testFrequency = 1f
    private val testGoalName = "Test Goal"

    override fun prepare() {
        viewModel = GoalFrequencyScreenViewModel(
            addGoalUseCase = addGoalUseCase,
            setOnboardingFinishedUseCase = setOnboardingFinishedUseCase,
            logger = logger,
        )
        everySuspend {
            addGoalUseCase.call(testGoalName to 3)
        } returns Result.success(Unit)
        everySuspend {
            setOnboardingFinishedUseCase.call(true)
        } returns Result.success(Unit)
    }

    @Test
    fun `calls addGoalUseCase with name and frequency from state`() =
        runTest {
            // WHEN action SaveGoal is called with testGoalName
            viewModel.sendAction(GoalFrequencyScreenAction.SaveGoal(testGoalName))

            // THEN addGoalUseCase is called once with testGoalName and testFrequency
            verifySuspend {
                addGoalUseCase.call(testGoalName to 3)
            }
            verifyNoMoreCalls(addGoalUseCase)
        }

    @Test
    fun `calls setOnboardingFinishedUseCase with true`() =
        runTest {
            // WHEN action SaveGoal is called with testGoalName
            viewModel.sendAction(GoalFrequencyScreenAction.SaveGoal(testGoalName))

            // THEN setOnboardingFinishedUseCase is called once with true
            verifySuspend {
                setOnboardingFinishedUseCase.call(true)
            }
            verifyNoMoreCalls(setOnboardingFinishedUseCase)
        }

    @Test
    fun `updates frequency in state`() =
        runTest {
            // WHEN action UpdateFrequency is called with testFrequency
            viewModel.sendAction(GoalFrequencyScreenAction.UpdateFrequency(testFrequency))

            // THEN frequency in state is updated
            assertEquals(testFrequency, viewModel.state.value.frequency)
        }
}
