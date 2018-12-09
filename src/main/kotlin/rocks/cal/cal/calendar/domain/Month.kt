package rocks.cal.cal.calendar.domain

import arrow.optics.optics

@optics
data class Month(val month: String, val weeks: Map<Int, Week>) {
    companion object
}