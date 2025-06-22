package presentation.screen.home.viewModel

import domain.model.Goal
import domain.model.GoalState
import domain.useCase.MonitorGoalStateUseCase
import domain.util.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import presentation.screen.home.HomeScreenAction
import presentation.screen.home.HomeScreenState
import presentation.util.CoreViewModel
import presentation.util.getLocalDateTime
import util.Logger

class HomeScreenViewModel(
    private val emitAllGoalsUseCase: UseCase<Unit, Flow<List<Goal>>>,
    private val editGoalUseCase: UseCase<Goal, Unit>,
    private val monitorGoalStateUseCase: UseCase<Pair<Goal, LocalDate>, GoalState>,
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
            emitAllGoalsUseCase.call(value = Unit).onSuccess { goalsFlow ->
                goalsFlow.collect { goals ->

                    // Only refresh states when goals are first loaded
                    if (state.value.goals.isEmpty() && goals.isNotEmpty()) {
                        goals.forEach { goal ->
                            monitorGoalStateUseCase.call(
                                goal to getLocalDateTime().date
                            ).onSuccess { state ->
                                editGoalUseCase.call(
                                    goal.copy(
                                        state = state,
                                    )
                                )
                            }
                        }
                    }

                    _state.update { state ->
                        state.copy(goals = goals)
                    }
                }
            }
        }
    }

    override fun HomeScreenAction.process() {
        when (val action = this@process) {
            is HomeScreenAction.UpdateGoalState -> {
                vmScope.launch {
                    val goal = state.value.getGoalWithId(action.id)

                    val newCompletions = if (action.newState == GoalState.DONE) {
                        goal.completions.filterOutToday() + getLocalDateTime()
                    } else {
                        goal.completions.filterOutToday()
                    }

                    editGoalUseCase.call(
                        value = goal.copy(
                            state = action.newState,
                            completions = newCompletions
                        ),
                    )
                }
            }
        }
    }

    private fun HomeScreenState.getGoalWithId(id: Long): Goal {
        return this.goals.first { it.id == id }
    }

    private fun List<LocalDateTime>.filterOutToday(): List<LocalDateTime> {
        val today = getLocalDateTime().date
        return this.filter { it.date != today }
    }
}
