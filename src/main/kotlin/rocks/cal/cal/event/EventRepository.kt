package rocks.cal.cal.event

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface EventRepository : CrudRepository<Event, Long> {
    @Query(value = "SELECT * FROM Event WHERE extract(year from date) = (:year) ORDER BY date", nativeQuery = true)
    fun forYear(@Param("year") year: Int): Iterable<Event>

    @Query(value = "SELECT * FROM Event WHERE date = (:date)", nativeQuery = true)
    fun forDate(@Param("date") date: LocalDate): Event?
}