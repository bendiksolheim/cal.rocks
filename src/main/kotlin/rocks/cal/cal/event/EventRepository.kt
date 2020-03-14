package rocks.cal.cal.event

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface EventRepository : SqlObject {
    @SqlQuery(value = SELECT_FOR_YEAR)
    fun forYear(year: Int): List<Event>

    @SqlQuery(value = SELECT_FOR_DATE)
    fun forDate(date: LocalDate): Event?

    @SqlUpdate("INSERT INTO event (id, date, tag_id) VALUES (NEXTVAL('hibernate_sequence'), :event.date, :event.tag.id)")
    @GetGeneratedKeys("id")
    fun insert(event: Event): Long

    @SqlQuery("SELECT * FROM event WHERE id=:id")
    fun findById(id: Long): Event

    fun save(event: Event): Event {
        val id = insert(event)
        return findById(id)
    }
}

const val SELECT_FOR_YEAR = """
    SELECT event.id, event.date,
           tag.id AS tag_id, tag.background AS tag_background, tag.name AS tag_name, tag.foreground AS tag_foreground
    FROM event
    INNER JOIN tag ON tag.id=event.tag_id
    WHERE extract(year from event.date) = :year
    ORDER BY date
"""

const val SELECT_FOR_DATE = """
    SELECT event.id, event.date,
           tag.id AS tag_id, tag.background AS tag_background, tag.name AS tag_name, tag.foreground AS tag_foreground
    FROM event
    INNER JOIN tag ON tag.id=event.tag_id
    WHERE event.date = :date
"""