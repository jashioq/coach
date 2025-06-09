package presentation.screen.home.viewModel

import domain.model.Goal
import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.home.HomeScreenAction
import presentation.screen.home.HomeScreenState
import presentation.util.CoreViewModel
import util.Logger

class HomeScreenViewModel(
    private val emitAllGoalsUseCase: UseCase<Unit, Flow<List<Goal>>>,
    private val editGoalUseCase: UseCase<Goal, Unit>,
    scope: CoroutineScope? = null,
    logger: Logger? = null,
) : CoreViewModel<HomeScreenState, HomeScreenAction>(
    initialState = HomeScreenState(
        goals = emptyList(),
    ),
    scope = scope,
    logger = logger,
) {
    init {
        vmScope.launch {
            emitAllGoalsUseCase.call(value = Unit).onSuccess {
                it.collect { goals ->
                    _state.update { state ->
                        state.copy(
                            goals = goals,
                        )
                    }
                }
            }
        }
    }

    fun dispatch(action: HomeScreenAction) =
        action.process {
            when (it) {
                is HomeScreenAction.UpdateGoalState -> {
                    vmScope.launch {
                        val goal = state.value.goals.first { goal -> goal.id == it.id }
                        Logger().d("dupa", "new goal is ${goal.copy(state = it.newState)}")
                        editGoalUseCase.call(
                            value = goal.copy(state = it.newState),
                        )
                    }
                }
            }
        }
}
