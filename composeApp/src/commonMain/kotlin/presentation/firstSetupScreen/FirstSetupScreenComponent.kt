package presentation.firstSetupScreen

import com.arkivanov.decompose.ComponentContext

class FirstSetupScreenComponent(
    componentContext: ComponentContext,
    private val onComplete: () -> Unit,
) : ComponentContext by componentContext {

    fun onEvent(event: FirstSetupScreenEvent) {
        when (event) {
            FirstSetupScreenEvent.ClickButton -> onComplete()
        }
    }
}
