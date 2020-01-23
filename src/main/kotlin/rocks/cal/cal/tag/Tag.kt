package rocks.cal.cal.tag

import javax.persistence.*

@Entity
data class Tag(
        val name: String = "",
        val background: String = "",
        val foreground: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = -1)