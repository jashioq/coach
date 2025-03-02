package navigation.screenB

sealed interface ScreenBEvent {
    data object ClickBack : ScreenBEvent
}
