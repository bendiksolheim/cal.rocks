package rocks.cal.cal.html

import kotlinx.html.*
import rocks.cal.cal.calendar.domain.Day
import rocks.cal.cal.calendar.domain.Month
import rocks.cal.cal.calendar.domain.Week
import rocks.cal.cal.calendar.domain.Year

fun calendarPage(year: Year): String {
    return page {
        yearSection(year)
    }
}

private fun DIV.yearSection(year: Year) =
        div("year") {
            h1("year__title") {
                +year.year.toString()
            }

            div("year__months") {
                year.months.map (::monthSection)
            }
        }

private fun DIV.monthSection(month: Month) =
        div("month") {
            div("month__title") {
                +month.month
            }

            div("month__weeks") {
                div("month__days") {
                    div("month__spacer") { +"" }
                    days.map (::dayNameSection)
                }
                month.weeks.map (::weekSection)
            }
        }

private fun DIV.weekSection(week: Map.Entry<Int, Week>) =
        div("week") {
            div("week__number") {
                +week.key.toString()
            }
            if (week.value.days.first().date == 1) {
                IntRange(0, 6 - week.value.days.size).map (::emptyDaySection)
            }
            week.value.days.map (::daySection)
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

private fun DIV.daySection(day: Day) =
        div(dayClass(day)) {
            attributes["data-day"] = day.date.toString()
            if (day.holiday != null) {
                attributes["data-holiday"] = day.holiday
            }
            div("day__date") {
                +day.date.toString()
            }
            div("day__background")
        }

private fun dayClass(day: Day) =
        day.holiday
                ?.let { "week__day day day--holiday" }
                ?: "week__day day"

private val days = listOf("Ma", "Ti", "On", "To", "Fr", "Lø", "Sø")