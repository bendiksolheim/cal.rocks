package rocks.cal.cal.calendar.domain

import arrow.optics.optics

@optics
data class Week(val number: Int, val days: List<Day>) {
    companion object
}