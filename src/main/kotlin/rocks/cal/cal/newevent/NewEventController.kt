package rocks.cal.cal.newevent

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import rocks.cal.cal.event.EventRepository
import rocks.cal.cal.html.newEvent
import rocks.cal.cal.tag.TagRepository
import java.time.LocalDate

@RestController
class NewEventController
@Autowired constructor(private val events: EventRepository, private val tags: TagRepository) {

    @GetMapping("/event/{date}")
    fun eventPage(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): String =
            events.forDate(date)
                    ?.let { newEvent(it, tags.findAll().toList()) }
                    ?: newEvent(date, tags.findAll().toList())
}