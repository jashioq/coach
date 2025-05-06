package presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = koinViewModel(),
) {
    val userName by homeScreenViewModel.userName.collectAsState()
    val goalId by homeScreenViewModel.goalId.collectAsState()
    val goalName by homeScreenViewModel.goalName.collectAsState()
    val goalFrequency by homeScreenViewModel.goalFrequency.collectAsState()

    HomeScreenView(
        userName = userName,
        goalId = goalId,
        goalName = goalName,
        goalFrequency = goalFrequency,
    )
}
