package presentation.screenA

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import presentation.koinViewModel

@Composable
fun ScreenA(
    screenAViewModel: ScreenAViewModel = koinViewModel(),
    onNavigateToScreenB: (Int) -> Unit,
) {
    val counter by screenAViewModel.counter.collectAsState()

    ScreenAView(
        onMainButtonClick = {
            onNavigateToScreenB(counter)
        },
        onIncrementButtonClick = {
            screenAViewModel.action(
                ScreenAAction.IncrementCounter,
            )
        },
        onResetButtonClick = {
            screenAViewModel.action(
                ScreenAAction.SetCounter(0),
            )
        },
        counter = counter,
    )
}
