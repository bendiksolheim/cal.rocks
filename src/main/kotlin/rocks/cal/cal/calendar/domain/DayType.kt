package rocks.cal.cal.calendar.domain

sealed class DayType
object Normal: DayType()
object Weekend: DayType()
data class Holiday(val name: String): DayType()