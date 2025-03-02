package screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navigation.onboardingStack.firstSetupScreen.FirstSetupScreenComponent
import navigation.onboardingStack.firstSetupScreen.FirstSetupScreenEvent

@Composable
fun FirstSetupScreen(component: FirstSetupScreenComponent) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("FirstSetupScreen")
        Button(
            onClick = {
                component.onEvent(FirstSetupScreenEvent.ClickButton)
            },
        ) {
            Text("Go to RootStack")
        }
    }
}