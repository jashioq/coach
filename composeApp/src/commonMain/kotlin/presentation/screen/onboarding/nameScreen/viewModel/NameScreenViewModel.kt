package presentation.screen.onboarding.nameScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.util.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.screen.onboarding.nameScreen.NameScreenAction

class NameScreenViewModel(
    private val setUserNamePreferenceUseCase: UseCase<String, Unit>,
) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun action(action: NameScreenAction) {
        when (action) {
            is NameScreenAction.UpdateName -> {
                _name.value = action.newValue
            }

            NameScreenAction.SaveName -> {
                viewModelScope.launch {
                    setUserNamePreferenceUseCase.call(name.value.trim())
                }
            }
        }
    }
}
