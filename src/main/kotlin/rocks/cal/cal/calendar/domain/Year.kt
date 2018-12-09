package rocks.cal.cal.calendar.domain

import arrow.optics.optics

@optics
data class Year(val year: Int, val months: List<Month>) {
    companion object
}