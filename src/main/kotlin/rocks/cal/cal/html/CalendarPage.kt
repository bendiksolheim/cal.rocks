package rocks.cal.cal.html

import kotlinx.html.*
import rocks.cal.cal.calendar.domain.Day
import rocks.cal.cal.calendar.domain.Month
import rocks.cal.cal.calendar.domain.Year

fun calendarPage(year: Year): String {
    return page {
        yearSection(year)
    }
}

private fun DIV.yearSection(year: Year) =
        div("year") {
            div("year__title") {
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

            div("month__days") {
                month.days.map (::daySection)
            }
        }

private fun DIV.daySection(day: Day) =
        div("day") {
            +day.date.toString()
        }