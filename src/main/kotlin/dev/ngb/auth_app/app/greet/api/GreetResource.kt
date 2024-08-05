package dev.ngb.auth_app.app.greet.api

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class GreetResource : GreetApi {
    override fun hello(): Map<String, String> {
        return mapOf("result" to "Hello, world!")
    }

    override fun whoAmI(user: Principal): Map<String, Any> {
        user as JwtAuthenticationToken
        return mapOf("result" to user.tokenAttributes)
    }
}