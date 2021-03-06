package rocks.cal.cal.html

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import rocks.cal.cal.CalApplication

fun page(title: String, _body: DIV.() -> Unit): String =
    StringBuilder().appendln("<!DOCTYPE html>").appendHTML(prettyPrint = false)
        .html {
            htmlHead(title)
            body {
                div(classes = "main", block = _body)
            }
        }.toString()

private fun css(): String =
    CalApplication::class.java.classLoader.getResource("static/index.css").readText()

private fun HTML.htmlHead(title: String) =
        head {
            title(title)
            style { +css() }
            meta(name = "viewport", content = "width=device-width, initial-scale=1")
        }
