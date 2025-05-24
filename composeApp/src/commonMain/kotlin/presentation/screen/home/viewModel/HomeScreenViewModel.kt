package presentation.screen.home.viewModel

import domain.model.Goal
import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.home.HomeScreenState
import presentation.util.CoreViewModel
import util.Logger

class HomeScreenViewModel(
    private val emitUserNamePreferenceUseCase: UseCase<Unit, Flow<String>>,
    private val emitAllGoalsUseCase: UseCase<Unit, Flow<List<Goal>>>,
    scope: CoroutineScope? = null,
    logger: Logger? = null,
) : CoreViewModel<HomeScreenState, Unit>(
    initialState = HomeScreenState(
        userName = "",
        goalId = "",
        goalName = "",
        goalFrequency = "",
    ),
    scope = scope,
    logger = logger,
) {
    init {
        vmScope.launch {
            emitUserNamePreferenceUseCase.call(value = Unit).onSuccess {
                it.collect { name ->
                    _state.update { state ->
                        state.copy(
                            userName = name,
                        )
                    }
                }
            }
        }
        vmScope.launch {
            emitAllGoalsUseCase.call(value = Unit).onSuccess {
                it.collect { goals ->
                    _state.update { state ->
                        state.copy(
                            goalId = goals.first().id.toString(),
                            goalName = goals.first().name,
                            goalFrequency = goals.first().frequency.toString(),
                        )
                    }
                }
            }
        }
    }
}
