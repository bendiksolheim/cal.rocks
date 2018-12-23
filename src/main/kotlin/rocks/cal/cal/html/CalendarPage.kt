package rocks.cal.cal.html

import kotlinx.html.*
import rocks.cal.cal.calendar.domain.Day
import rocks.cal.cal.calendar.domain.Month
import rocks.cal.cal.calendar.domain.Week
import rocks.cal.cal.calendar.domain.Year

fun calendarPage(year: Year, today: Pair<Int, Int>): String {
    return page {
        yearSection(year, today)
    }
}

private fun DIV.yearSection(year: Year, today: Pair<Int, Int>) =
        div("year") {
            h1("year__title") {
                +year.year.toString()
            }

            div("year__months") {
                year.months.map { monthSection(it, sameMonth(today, it)) }
            }
        }

private fun DIV.monthSection(month: Month, dayOfMonth: Int?) =
        div("month") {
            div("month__title") {
                +month.month
            }

            div("month__weeks") {
                div("month__days") {
                    div("month__spacer") { +"" }
                    days.map (::dayNameSection)
                }
                month.weeks.map { weekSection(it, dayOfMonth) }
            }
        }

private fun DIV.weekSection(week: Map.Entry<Int, Week>, dayOfMonth: Int?) =
        div("week") {
            div("week__number") {
                +week.key.toString()
            }
            if (week.value.days.first().date == 1) {
                IntRange(0, 6 - week.value.days.size).map (::emptyDaySection)
            }
            week.value.days.map { daySection(it, sameDate(it, dayOfMonth)) }
            if (week.value.days.size != 7 && week.value.days.first().date != 1) {
                IntRange(0, 6 - week.value.days.size).map (::emptyDaySection)
            }
        }

private fun DIV.dayNameSection(day: String) =
        div("month__day-name") {
            +day
        }

private fun DIV.emptyDaySection(unused: Int) =
        div("week__day week__day--empty") {
            +""
        }

private fun DIV.daySection(day: Day, today: Boolean) =
        div(dayClass(day, today)) {
            attributes["data-day"] = day.date.toString()
            if (day.holiday != null) {
                attributes["data-holiday"] = day.holiday
            }
            div("day__date") {
                +day.date.toString()
            }
            div("day__background")
        }

private fun dayClass(day: Day, today: Boolean) = listOf(
                Pair("week__day", true),
                Pair("day", true),
                Pair("day--holiday", day.holiday != null),
                Pair("day--today", today)
            ).filter { it.second }
            .joinToString(" ") { it.first }

private val days = listOf("Ma", "Ti", "On", "To", "Fr", "Lø", "Sø")

private fun sameMonth(today: Pair<Int, Int>, it: Month): Int? =
        if (today.first == it.number) {
            today.second
        } else {
            null
        }

private fun sameDate(day: Day, dayOfMonth: Int?): Boolean =
        day.date == dayOfMonth