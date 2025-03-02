package presentation.onboardingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun OnboardingScreen(component: OnboardingScreenComponent) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("OnboardingScreen")
        Button(
            onClick = {
                component.onEvent(OnboardingScreenEvent.ClickButton)
            },
        ) {
            Text("Go to FirstSetupScreen")
        }
    }
}
