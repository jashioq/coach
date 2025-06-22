package domain.model

import kotlinx.datetime.LocalDateTime

data class Goal(
    val id: Long,
    val name: String,
    val frequency: Int,
    val state: GoalState,
    val completions: List<LocalDateTime>,
)
