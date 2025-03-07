package presentation.screenA

open class ScreenAAction {

    data object IncrementCounter : ScreenAAction()

    data class SetCounter(
        val newValue: Int,
    ) : ScreenAAction()
}
