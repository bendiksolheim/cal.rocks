package rocks.cal.cal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import rocks.cal.cal.calendar.CalendarService
import rocks.cal.cal.html.calendarPage

@RestController
class CalendarController
@Autowired constructor(private val calendarService: CalendarService){

    @GetMapping("/")
    fun index(): String = calendarPage(calendarService.get(2018))
}