package navigation.rootStack.screenB

sealed interface ScreenBEvent {
    data object ClickBack : ScreenBEvent
}
