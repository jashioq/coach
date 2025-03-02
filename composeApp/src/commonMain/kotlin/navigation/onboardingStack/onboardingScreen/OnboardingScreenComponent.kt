package navigation.onboardingStack.onboardingScreen

import com.arkivanov.decompose.ComponentContext

class OnboardingScreenComponent(
    componentContext: ComponentContext,
    private val onNavigateForward: () -> Unit,
) : ComponentContext by componentContext {

    fun onEvent(event: OnboardingScreenEvent) {
        when (event) {
            OnboardingScreenEvent.ClickButton -> onNavigateForward()
        }
    }
}