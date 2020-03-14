package rocks.cal.cal.tag

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : SqlObject {
    @SqlQuery("SELECT * FROM tag")
    fun findAll(): List<Tag>

    @SqlQuery("SELECT * FROM tag WHERE id=:id")
    fun findById(id: Long): Tag

    @SqlQuery("SELECT * FROM tag WHERE id=:id")
    fun findByIdOrNull(id: Long): Tag?

    @SqlUpdate("INSERT INTO tag (id, name, background, foreground) VALUES (NEXTVAL('hibernate_sequence'), :tag.name, :tag.background, :tag.foreground)")
    @GetGeneratedKeys("id")
    fun insert(tag: Tag): Long

    fun save(tag: Tag): Tag {
        val id = insert(tag)
        return findById(id)
    }
}
