package presentation.screen.onboarding.startScreen.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class StartScreenViewModelTest {
    private lateinit var viewModel: StartScreenViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun beforeTest() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun afterTest() {
        Dispatchers.resetMain()
    }

    @Test
    fun `updates textIndex in state every 2 seconds`() {
        runTest {
            viewModel = StartScreenViewModel(scope = TestScope(testDispatcher))
            assertEquals(4, viewModel.state.value.textIndex)
            advanceTimeBy(2000)

            assertEquals(0, viewModel.state.value.textIndex)

//            for (i in 0 until 4) {
//                assertEquals(i, viewModel.state.value.textIndex)
//                testScheduler.advanceTimeBy(2000)
//            }
        }
    }
}
