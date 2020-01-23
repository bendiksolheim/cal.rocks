package rocks.cal.cal.event

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class NewEvent(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) val date: LocalDate,
        val tagId: Long)
