package rocks.cal.cal.html

import kotlinx.html.*
import rocks.cal.cal.event.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun newEvent(event: Event, tags: List<rocks.cal.cal.tag.Tag>): String =
        page("Edit Date") {
            div(classes = "new-event") {
                form(method = FormMethod.post, action = "/api/events", classes = "new-event__form") {
                    dateInput(event.date)
                    tagSelect(tags)
                    button(type = ButtonType.submit, classes = "new-event__save") { +"Save" }
                }
            }
        }

fun newEvent(date: LocalDate, tags: List<rocks.cal.cal.tag.Tag>): String =
        page("New Event") {
            div(classes = "new-event") {
                form(method = FormMethod.post, action = "/api/events", classes = "new-event__form") {
                    dateInput(date)
                    tagSelect(tags)
                    button(type = ButtonType.submit, classes = "new-event__save") { +"Save" }
                }
            }
        }

fun FORM.dateInput(date: LocalDate): Unit =
        div(classes = "new-event__date") {
            label {
                span(classes = "new-event__date-label") {+"Date" }
                input(type = InputType.text, classes = "new-event__date-input") {
                    attributes["readonly"] = ""
                    attributes["name"] = "date"
                    attributes["value"] = date.format(DateTimeFormatter.ISO_DATE)
                }
            }
        }

fun FORM.tagSelect(tags: List<rocks.cal.cal.tag.Tag>): Unit =
        div(classes = "new-event__tags") {
            label {
                span(classes = "new-event__tags-label") { +"Tag" }
                select(classes = "new-event__tags-input") {
                    attributes["name"] = "tagId"
                    tags.map { option {
                        attributes["value"] = it.id.toString()
                        +it.name
                    } }
                }
            }
        }