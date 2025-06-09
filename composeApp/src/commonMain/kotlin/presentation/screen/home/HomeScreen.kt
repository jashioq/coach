package presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.screen.home.viewModel.HomeScreenViewModel
import presentation.util.koinViewModel
import util.Logger

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = koinViewModel(),
) {
    val state by homeScreenViewModel.state.collectAsState()

    HomeScreenView(
        goals = state.goals,
        onGoalStateChange = { id, newState ->
            Logger().d("dupa", "goal with id: $id state changed: $newState")
            homeScreenViewModel.dispatch(
                HomeScreenAction.UpdateGoalState(
                    id = id,
                    newState = newState,
                ),
            )
        },
    )
}
