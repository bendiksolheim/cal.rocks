package rocks.cal.cal.calendar.domain

import arrow.optics.optics

@optics
data class Day(val date: Int, val type: DayType) {
    companion object
}