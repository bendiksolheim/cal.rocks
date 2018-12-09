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

            table("month__days") {
                tbody {
                    month.weeks.map (::weekSection)
                }
            }
        }

private fun TBODY.weekSection(week: Map.Entry<Int, Week>) =
        tr {
            week.value.days.map (::daySection)
        }

private fun TR.daySection(day: Day?) =
        td("day") {
            if (day == null) {
                br
            } else {
                + day.date.toString()
            }
        }