package rocks.cal.cal.calendar.domain

import arrow.optics.optics
import rocks.cal.cal.tag.Tag
import java.time.LocalDate

@optics
data class Day(val date: LocalDate, val type: DayType, val tag: Tag?) {
    companion object
}