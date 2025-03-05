package presentation.screenA

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import koinViewModel

@Composable
fun ScreenA(
    onNavigateToScreenB: (String, Int) -> Unit,
    screenAViewModel: ScreenAViewModel = koinViewModel(),
) {
    val counter by screenAViewModel.counter.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = counter.toString(),
        )
        Button(
            onClick = {
                screenAViewModel.action(ScreenAAction.IncrementCounter)
            },
        ) {
            Text("Increment counter")
        }
        Button(
            onClick = {
                screenAViewModel.action(ScreenAAction.SetCounter(0))
            },
        ) {
            Text("Reset counter")
        }
        Button(
            onClick = {
                onNavigateToScreenB("Jan", 22)
            },
        ) {
            Text("Go to screen B")
        }
    }
}
