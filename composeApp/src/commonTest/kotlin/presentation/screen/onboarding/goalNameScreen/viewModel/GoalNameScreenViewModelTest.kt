package presentation.screen.onboarding.goalNameScreen.viewModel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import presentation.screen.onboarding.goalNameScreen.GoalNameScreenAction
import util.ViewModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GoalNameScreenViewModelTest : ViewModelTest() {
    private lateinit var viewModel: GoalNameScreenViewModel
    private val testGoalName = "Test Goal"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updates goalNamePlaceholderIndex in state every 2 seconds`() =
        runTest {
            // GIVEN initial state
            viewModel = GoalNameScreenViewModel(scope = backgroundScope)

            // THEN updates goalNamePlaceholderIndex in state every 2 seconds
            assertEquals(9, viewModel.state.value.goalNamePlaceholderIndex)
            advanceTimeBy(2000)

            for (i in 0..9) {
                assertEquals(i, viewModel.state.value.goalNamePlaceholderIndex)
                advanceTimeBy(2000)
            }

            assertEquals(0, viewModel.state.value.goalNamePlaceholderIndex)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updates goalName in state`() =
        runTest {
            // GIVEN initial state
            viewModel = GoalNameScreenViewModel(scope = backgroundScope, logger = logger)

            // WHEN action UpdateGoalName is called with testGoalName
            viewModel.sendAction(GoalNameScreenAction.UpdateGoalName(testGoalName))

            runCurrent()

            // THEN goalName in state is updated
            assertEquals(testGoalName, viewModel.state.value.goalName)
        }
}
