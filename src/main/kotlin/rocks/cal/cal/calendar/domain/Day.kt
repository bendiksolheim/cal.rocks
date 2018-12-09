package rocks.cal.cal.calendar.domain

import arrow.optics.optics

@optics
data class Day(val date: String) {
    companion object
}