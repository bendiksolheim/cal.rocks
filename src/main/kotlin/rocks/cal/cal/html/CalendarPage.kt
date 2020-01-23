package rocks.cal.cal.html

import kotlinx.html.*
import rocks.cal.cal.calendar.domain.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun calendarPage(year: Year, today: Pair<Int, Int>): String {
    return page(year.year.toString()) {
        yearSection(year, today)
    }
}

private fun DIV.yearSection(year: Year, today: Pair<Int, Int>) =
        div("year") {
            div("year__heading") {
                a("/${year.year - 1}", classes = "year__previous-link") {
                    +"${year.year - 1}"
                }
                h1("year__title") {
                    +year.year.toString()
                }
                a("/${year.year + 1}", classes = "year__next-link") {
                    +"${year.year + 1}"
                }
            }

            div("year__months") {
                year.months.map { monthSection(it, sameMonth(today, it)) }
            }
        }

private fun DIV.yearLink(year: Year, classes: String) =
        a(year.year.minus(1).toString(), classes = classes) {
            attributes["title"] = year.year.minus(1).toString()
            +"<"
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
            if (week.value.days.first().date.dayOfMonth == 1) {
                IntRange(0, 6 - week.value.days.size).map (::emptyDaySection)
            }
            week.value.days.map { daySection(it, sameDate(it, dayOfMonth)) }
            if (week.value.days.size != 7 && week.value.days.first().date.dayOfMonth != 1) {
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
            if (day.type is Holiday) {
                attributes["data-title"] = day.type.name
            }
            if (day.tag != null) {
                attributes["style"] = "color: ${day.tag.foreground}"
                attributes["data-title"] = day.tag.name
            }
            if (day.tag != null && day.type is Holiday) {
                attributes["data-title"] = "${day.tag.name}, ${day.type.name}"
            }
            a(href = "/event/${formatDate(day.date)}", classes = "day__date") {
                +day.date.dayOfMonth.toString()
            }
            div("day__background") {
                if (day.tag != null) {
                    attributes["style"] = "background: ${day.tag.background}"
                }
            }
        }

private fun dayClass(day: Day, today: Boolean) = listOf(
                Pair("week__day", true),
                Pair("day", true),
                Pair("day--holiday", day.type is Holiday),
                Pair("day--weekend", day.type is Weekend),
                Pair("day--today", today),
                Pair("day--title", day.tag != null || day.type is Holiday)
            ).filter { it.second }
            .joinToString(" ") { it.first }

private fun formatDate(date: LocalDate): String =
        date.format(DateTimeFormatter.ISO_DATE)

private val days = listOf("Ma", "Ti", "On", "To", "Fr", "Lø", "Sø")

private fun sameMonth(today: Pair<Int, Int>, it: Month): Int? =
        if (today.first == it.number) {
            today.second
        } else {
            null
        }

private fun sameDate(day: Day, dayOfMonth: Int?): Boolean =
        day.date.dayOfMonth == dayOfMonth