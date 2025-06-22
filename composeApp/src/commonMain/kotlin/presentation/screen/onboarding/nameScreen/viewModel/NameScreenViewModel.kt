package presentation.screen.onboarding.nameScreen.viewModel

import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.onboarding.nameScreen.NameScreenAction
import presentation.screen.onboarding.nameScreen.NameScreenState
import presentation.util.CoreViewModel
import util.Logger

/**
 * View model used for handling name screen state and actions.
 * @param setUserNamePreferenceUseCase a [UseCase] used for setting user name preference.
 */
class NameScreenViewModel(
    private val setUserNamePreferenceUseCase: UseCase<String, Unit>,
    scope: CoroutineScope? = null,
    logger: Logger? = null,
) : CoreViewModel<NameScreenState, NameScreenAction>(
    initialState = NameScreenState(
        name = "",
    ),
    scope = scope,
    logger = logger,
) {
    override fun NameScreenAction.process() {
        when (val action = this@process) {
            is NameScreenAction.UpdateName -> {
                _state.update { state ->
                    state.copy(
                        name = action.newValue,
                    )
                }
            }

            NameScreenAction.SaveName -> {
                vmScope.launch {
                    setUserNamePreferenceUseCase.call(state.value.name.trim())
                }
            }
        }
    }
}
