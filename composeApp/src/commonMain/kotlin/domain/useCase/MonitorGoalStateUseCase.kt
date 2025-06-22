package domain.useCase

import domain.model.Goal
import domain.model.GoalState
import domain.util.UseCase
import kotlinx.datetime.LocalDate

class MonitorGoalStateUseCase : UseCase<Pair<Goal, LocalDate>, GoalState> {
    override suspend fun call(value: Pair<Goal, LocalDate>): Result<GoalState> {
        val latestCompletion = value.first.completions.maxOrNull()
        if (latestCompletion?.date == value.second) {
            return Result.success(GoalState.DONE)
        }
        return Result.success(GoalState.ACTIVE)
    }
}