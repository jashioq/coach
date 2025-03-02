package presentation.firstSetupScreen

sealed interface FirstSetupScreenEvent {
    data object ClickButton : FirstSetupScreenEvent
}
