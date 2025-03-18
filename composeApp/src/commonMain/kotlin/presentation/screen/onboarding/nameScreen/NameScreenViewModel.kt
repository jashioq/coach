package presentation.screen.onboarding.nameScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.useCase.SetNamePreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NameScreenViewModel(
    private val setNamePreferenceUseCase: SetNamePreferenceUseCase,
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
                    setNamePreferenceUseCase.call(name.value)
                }
            }
        }
    }
}
