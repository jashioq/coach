package presentation.screenB

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import domain.model.Goal

@Composable
fun ScreenBView(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    count: Int,
    goals: List<Goal>,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Current count is $count")
        Button(onClick = onBackButtonClick) {
            Text("Go back")
        }
        Text("There are ${goals.size} goals")
    }
}
