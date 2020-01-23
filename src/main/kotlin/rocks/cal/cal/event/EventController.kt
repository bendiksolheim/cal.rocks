package rocks.cal.cal.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rocks.cal.cal.tag.Tag
import rocks.cal.cal.tag.TagRepository
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(path = ["/api/events"])
class EventController
@Autowired constructor(
        private val events: EventRepository,
        private val tags: TagRepository) {

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE] )
    fun create(event: NewEvent, response: HttpServletResponse) {
        val tag: Tag? = tags.findByIdOrNull(event.tagId)
        if (tag == null) {
            response.status = HttpStatus.BAD_REQUEST.value()
        } else {
            val ev = events.forDate(event.date)
                    ?.copy(tag = tag)
                    ?: Event(event.date, tag)

            events.save(ev)
            response.sendRedirect("/")
        }
    }
}