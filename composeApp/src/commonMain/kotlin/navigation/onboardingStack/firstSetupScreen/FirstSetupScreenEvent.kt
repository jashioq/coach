package navigation.onboardingStack.firstSetupScreen

sealed interface FirstSetupScreenEvent {
    data object ClickButton : FirstSetupScreenEvent
}