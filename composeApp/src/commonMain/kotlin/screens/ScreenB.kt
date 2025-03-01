package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navigation.screenB.ScreenBComponent
import navigation.screenB.ScreenBEvent

@Composable
fun ScreenB(text: String, component: ScreenBComponent) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("ScreenB, text is $text")
        Button(
            onClick = {
                component.onEvent(ScreenBEvent.ClickBack)
            }
        ) {
            Text("Go back")
        }
    }
}