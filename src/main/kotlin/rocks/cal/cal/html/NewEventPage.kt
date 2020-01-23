package rocks.cal.cal.html

import kotlinx.html.*
import rocks.cal.cal.event.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun newEvent(event: Event, tags: List<rocks.cal.cal.tag.Tag>): String =
        page("Edit Date") {
            form(method = FormMethod.post, action = "/api/events") {
                dateInput(event.date)
                tagSelect(tags)
                button(type = ButtonType.submit) { +"Save" }
            }
        }

fun newEvent(date: LocalDate, tags: List<rocks.cal.cal.tag.Tag>): String =
        page("New Event") {
            form(method = FormMethod.post, action = "/api/events") {
                dateInput(date)
                tagSelect(tags)
                button(type = ButtonType.submit) { +"Save" }
            }
        }

fun FORM.dateInput(date: LocalDate): Unit =
        div {
            label {
                +"Date"
                input(type = InputType.date) {
                    attributes["readonly"] = ""
                    attributes["name"] = "date"
                    attributes["value"] = date.format(DateTimeFormatter.ISO_DATE)
                }
            }
        }

fun FORM.tagSelect(tags: List<rocks.cal.cal.tag.Tag>): Unit =
        div {
            label {
                +"Tag"
                select {
                    attributes["name"] = "tagId"
                    tags.map { option {
                        attributes["value"] = it.id.toString()
                        +it.name
                    } }
                }
            }
        }