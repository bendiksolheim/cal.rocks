package rocks.cal.cal.tag

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        path = ["/api/tags"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
class TagController
@Autowired constructor(private val tags: TagRepository) {

    @GetMapping
    fun list(): List<Tag> =
            tags.findAll().toList()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody tag: NewTag): Tag =
            tags.save(Tag(tag.name, tag.background, tag.foreground))
}