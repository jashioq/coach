package presentation.screenB

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun ScreenB(
    screenBViewModel: ScreenBViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    count: Int,
) {
    val goals by screenBViewModel.goals.collectAsState()
    ScreenBView(
        onBackButtonClick = onNavigateBack,
        count = count,
        goals = goals,
    )
}
