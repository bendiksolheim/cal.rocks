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

            table("month__weeks") {
                tbody {
                    tr("month__days") {
                        td { +"" }
                        days.map (::dayNameSection)
                    }
                    month.weeks.map (::weekSection)
                }
            }
        }

private fun TBODY.weekSection(week: Map.Entry<Int, Week>) =
        tr("week") {
            td("week__number") {
                +week.key.toString()
            }
            if (week.value.days.first() == Day(1)) {
                IntRange(0, 6 - week.value.days.size).map (::emptyDaySection)
            }
            week.value.days.map (::daySection)
            if (week.value.days.size != 7 && week.value.days.first() != Day(1)) {
                IntRange(0, 6 - week.value.days.size).map (::emptyDaySection)
            }
        }

private fun TR.dayNameSection(day: String) =
        td("month__day-name") {
            +day
        }

private fun TR.emptyDaySection(unused: Int) =
        td("week__day week__day--empty") {
            +""
        }

private fun TR.daySection(day: Day) =
        td("week__day") {
            +day.date.toString()
        }

private val days = listOf("Ma", "Ti", "On", "To", "Fr", "Lø", "Sø")