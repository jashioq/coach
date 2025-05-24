package presentation.screen.onboarding.startScreen.viewModel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import util.ViewModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class StartScreenViewModelTest : ViewModelTest() {
    private lateinit var viewModel: StartScreenViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updates textIndex in state every 2 seconds`() =
        runTest {
            // GIVEN initial state
            viewModel = StartScreenViewModel(scope = backgroundScope)

            // THEN updates textIndex in state every 2 seconds
            assertEquals(4, viewModel.state.value.textIndex)
            advanceTimeBy(2000)

            for (i in 0..4) {
                assertEquals(i, viewModel.state.value.textIndex)
                advanceTimeBy(2000)
            }

            assertEquals(0, viewModel.state.value.textIndex)
        }
}
