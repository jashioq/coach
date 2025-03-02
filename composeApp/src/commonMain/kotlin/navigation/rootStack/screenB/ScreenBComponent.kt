package navigation.rootStack.screenB

import com.arkivanov.decompose.ComponentContext

class ScreenBComponent(
    val text: String,
    private val onBack: () -> Unit,
    componentContext: ComponentContext
): ComponentContext by componentContext {

    fun onEvent(event: ScreenBEvent) {
        when (event) {
            ScreenBEvent.ClickBack -> onBack()
        }
    }
}