package rocks.cal.cal.event

import rocks.cal.cal.tag.Tag
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Event(
        val date: LocalDate = LocalDate.now(),
        @ManyToOne @JoinColumn val tag: Tag = Tag(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = -1)