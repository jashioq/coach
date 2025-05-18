package presentation.screen.onboarding.nameScreen

import androidx.lifecycle.viewModelScope
import domain.util.UseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.util.CoreViewModel

class NameScreenViewModel(
    private val setUserNamePreferenceUseCase: UseCase<String, Unit>,
) : CoreViewModel<NameScreenState, NameScreenAction>(
    initialState = NameScreenState(
        name = "",
    ),
) {
    fun dispatch(action: NameScreenAction) =
        action.process {
            when (it) {
                is NameScreenAction.UpdateName -> {
                    _state.update { state ->
                        state.copy(
                            name = it.newValue,
                        )
                    }
                }

                NameScreenAction.SaveName -> {
                    viewModelScope.launch {
                        setUserNamePreferenceUseCase.call(state.value.name.trim())
                    }
                }
            }
        }
}
