package presentation.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

fun getWeekBounds(): Pair<LocalDate, LocalDate> {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

    // Calculate Monday
    val monday = today.minus(today.dayOfWeek.isoDayNumber - DayOfWeek.MONDAY.isoDayNumber, kotlinx.datetime.DateTimeUnit.DAY)

    // Calculate Sunday
    val sunday = today.plus(DayOfWeek.SUNDAY.isoDayNumber - today.dayOfWeek.isoDayNumber, kotlinx.datetime.DateTimeUnit.DAY)

    return Pair(monday, sunday)
}

fun getLocalDateTime(): LocalDateTime {
    val nowInstant = Clock.System.now()
    val systemTimeZone = TimeZone.currentSystemDefault()
    return nowInstant.toLocalDateTime(systemTimeZone)
}