package rocks.cal.cal.calendar

import org.springframework.stereotype.Service
import rocks.cal.cal.calendar.domain.*
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*
import java.util.stream.Collectors

@Service
class CalendarService {

    fun get(year: Int) =
        datesForYear(year)
            .fold(Year(year, listOf())) { aYear, date ->
                val (newYear, month) = yearAndMonth(aYear, date)
                val (newMonth, week) = monthAndWeek(month, date)
                val weekNumber = date.get(weekNumber)
                val newWeek = Week.days.modify(week) { it + Day(date.dayOfMonth) }
                val m = Month.weeks.modify(newMonth) {it.minus(weekNumber) + Pair(weekNumber, newWeek)}
                Year.months.modify(newYear) {it.dropLast(1) + m}
            }
}

private fun monthName(date: LocalDate): String =
        date.month.getDisplayName(TextStyle.FULL, Locale("NO", "nb")).capitalize()

private fun yearAndMonth(year: Year, date: LocalDate): Pair<Year, Month> {
    val month = year.months.elementAtOrNull(date.monthValue - 1)
    return if (month == null) {
        val newYear = Year.months.modify(year) { it + Month(monthName(date), mapOf())}
        Pair(newYear, newYear.months.last())
    } else {
        Pair(year, month)
    }
}

private fun monthAndWeek(month: Month, date: LocalDate): Pair<Month, Week> {
    val week = month.weeks[date.get(weekNumber)]
    return if (week == null) {
        val weekNumber = date.get(weekNumber)
        val newWeek = Week(weekNumber, listOf())
        val newMonth = Month.weeks.modify(month) { it + Pair(weekNumber, newWeek) }
        Pair(newMonth, newWeek)
    } else {
        Pair(month, week)
    }
}

private fun datesForYear(year: Int): List<LocalDate> {
    val first = LocalDate.of(year, 1, 1)
    val last = LocalDate.of(year, 12, 31)
    return first.datesUntil(last.plusDays(1)).collect(Collectors.toList())
}

val weekNumber = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()