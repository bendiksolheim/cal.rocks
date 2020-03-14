package rocks.cal.cal

import org.apache.logging.slf4j.SLF4JLogger
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.postgres.PostgresPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import rocks.cal.cal.event.EventRepository
import rocks.cal.cal.tag.TagRepository
import javax.sql.DataSource

@Configuration
class Config {
    @Bean
    fun jdbi(ds: DataSource): Jdbi {
        val proxy = TransactionAwareDataSourceProxy(ds)
        val jdbi = Jdbi.create(proxy)
        jdbi.installPlugin(PostgresPlugin())
        jdbi.installPlugin(SqlObjectPlugin())
        jdbi.installPlugin(KotlinPlugin())
        jdbi.installPlugin(KotlinSqlObjectPlugin())
        return jdbi
    }

    @Bean
    fun eventRepository(jdbi: Jdbi): EventRepository =
            jdbi.onDemand(EventRepository::class.java)

    @Bean
    fun tagRepository(jdbi: Jdbi): TagRepository =
            jdbi.onDemand(TagRepository::class.java)
}