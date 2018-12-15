package rocks.cal.cal.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ErrorController: ErrorController {
    override fun getErrorPath() = "/error"

    @GetMapping("/error")
    fun error() = "What?"
}