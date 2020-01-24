package rocks.cal.cal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class CalApplication

fun main(args: Array<String>) {
    Locale.setDefault(Locale("nb", "NO"))
    runApplication<CalApplication>(*args)
}
