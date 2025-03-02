package navigation.onboardingStack.onboardingScreen

sealed interface OnboardingScreenEvent {
    data object ClickButton : OnboardingScreenEvent
}