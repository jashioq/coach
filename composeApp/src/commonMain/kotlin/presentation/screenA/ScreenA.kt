package presentation.screenA

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import koinViewModel

@Composable
fun ScreenA(
    onNavigateToScreenB: (Int) -> Unit,
    screenAViewModel: ScreenAViewModel = koinViewModel(),
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
