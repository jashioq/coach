package presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.compose.component.blob.BlobMode
import presentation.compose.component.blob.BlobState
import presentation.screen.home.viewModel.HomeScreenViewModel
import presentation.util.koinViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = koinViewModel(),
    blobState: BlobState,
    onSetBlobMode: (BlobMode) -> Unit,
) {
    val state by homeScreenViewModel.state.collectAsState()

    HomeScreenView(
        goals = state.goals,
        onGoalStateChange = { id, newState ->
            homeScreenViewModel.sendAction(
                HomeScreenAction.UpdateGoalState(
                    id = id,
                    newState = newState,
                ),
            )
        },
        blobState = blobState,
        onSetBlobMode = onSetBlobMode,
    )
}
