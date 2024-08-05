package dev.ngb.auth_app.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val authenticationEntryPoint: AuthenticationEntryPoint,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
            it.anyRequest().permitAll() // Permit all requests
        }
        http.oauth2ResourceServer {
            it.jwt { }.authenticationEntryPoint(authenticationEntryPoint)
        }
        http.csrf { it.disable() } // Disable CSRF
        http.cors { it.disable() } // Disable CORS
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { _ ->
            throw UsernameNotFoundException("Not found")
        }
    }
}