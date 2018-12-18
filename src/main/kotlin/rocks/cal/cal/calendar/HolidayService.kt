package rocks.cal.cal.calendar

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HolidayService {

    private val yearMap = mutableMapOf<Int, Map<LocalDate, String>>()

    constructor() {
        val currentYear = LocalDate.now().year
    }

    private val dates = mapOf<LocalDate, String>(
            Pair(LocalDate.now(), "")
    )

    fun isHoliday(date: LocalDate): String? =
        yearMap.getOrPut(date.year) { generateHolidays(date.year) }[date]

    fun generateHolidays(year: Int): Map<LocalDate, String> {
        return mutableMapOf(
                Pair(LocalDate.of(year, 1, 1), "Første nyttårsdag"),
                Pair(LocalDate.of(year, 12, 25), "Første juledag"),
                Pair(LocalDate.of(year, 12, 26), "Andre juledag")
        )
    }
}