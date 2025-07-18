package domain.util

import domain.model.Goal
import domain.model.GoalState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.jh.coach.data.local.database.GoalDto

fun GoalDto.toGoal(): Goal =
    Goal(
        id = this.id,
        name = this.name,
        frequency = this.frequency.toInt(),
        state = this.state.toGoalState(),
        completions = parseDateTimeString(this.completions),
    )

fun Long.toGoalState(): GoalState =
    when (this) {
        0L -> GoalState.ACTIVE
        else -> GoalState.DONE
    }

fun parseDateTimeString(input: String): List<LocalDateTime> {
    if (input.isEmpty()) return emptyList()
    val dateTimeStrings = input.split(',')
    return dateTimeStrings.map { dateTimeString ->
        val parts = dateTimeString.split(':')
        val dateParts = parts[0].split('.').map { it.toInt() }
        val timeParts = parts[1].split('-').map { it.toInt() }

        val date = LocalDate(dateParts[2], dateParts[1], dateParts[0])
        val time = LocalTime(timeParts[0], timeParts[1])

        LocalDateTime(date, time)
    }
}

fun formatLocalDateTimeList(dateTimes: List<LocalDateTime>): String {
    return dateTimes.joinToString(",") { dateTime ->
        val day = dateTime.dayOfMonth.toString().padStart(2, '0')
        val month = dateTime.monthNumber.toString().padStart(2, '0')
        val year = dateTime.year
        val hour = dateTime.hour.toString().padStart(2, '0')
        val minute = dateTime.minute.toString().padStart(2, '0')

        "$day.$month.$year:$hour-$minute"
    }
}
