package rocks.cal.cal.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import rocks.cal.cal.calendar.CalendarService
import rocks.cal.cal.html.calendarPage
import java.time.LocalDate

@RestController
class CalendarController
@Autowired constructor(private val calendarService: CalendarService){

    @GetMapping("/")
    fun index(): String = calendarPage(calendarService.get(LocalDate.now().year))

    @GetMapping("/{year}")
    fun year(@PathVariable year: String) =
            year.toIntOrNull()
                ?.let {calendarPage(calendarService.get(it))}
                ?: "$year is not a year, is it?"
}