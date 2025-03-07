package presentation.screenA

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenAView(
    modifier: Modifier = Modifier,
    onMainButtonClick: () -> Unit,
    onIncrementButtonClick: () -> Unit,
    onResetButtonClick: () -> Unit,
    counter: Int,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = counter.toString(),
        )
        Button(
            onClick = onIncrementButtonClick,
        ) {
            Text("Increment counter")
        }
        Button(
            onClick = onResetButtonClick,
        ) {
            Text("Reset counter")
        }
        Button(
            onClick = onMainButtonClick,
        ) {
            Text("Go to screen B")
        }
    }
}
