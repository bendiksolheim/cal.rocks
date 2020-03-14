package rocks.cal.cal.event

import org.jdbi.v3.core.mapper.Nested
import rocks.cal.cal.tag.Tag
import java.time.LocalDate

data class Event(
        val date: LocalDate = LocalDate.now(),
        @Nested("tag") val tag: Tag = Tag(),
        val id: Long = -1)