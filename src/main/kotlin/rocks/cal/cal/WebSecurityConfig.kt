package rocks.cal.cal

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.RequestMatcher

@Configuration
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.requiresChannel()
                .requestMatchers(RequestMatcher { it.getHeader("X-Forwarded-Proto") != null })
                .requiresSecure()
    }
}