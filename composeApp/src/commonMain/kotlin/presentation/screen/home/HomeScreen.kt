package presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = koinViewModel(),
) {
    val state by homeScreenViewModel.state.collectAsState()

    HomeScreenView(
        userName = state.userName,
        goalId = state.goalId,
        goalName = state.goalName,
        goalFrequency = state.goalFrequency,
    )
}
