package presentation.onboardingScreen

sealed interface OnboardingScreenEvent {
    data object ClickButton : OnboardingScreenEvent
}
