package dev.ngb.auth_app.app.greet.api

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@RequestMapping("/api/greet")
interface GreetApi {
    @RequestMapping("/hello")
    @PreAuthorize("permitAll")
    fun hello(): Map<String, Any>

    @RequestMapping("/who-am-i")
    @PreAuthorize("authenticated")
    fun whoAmI(user: Principal): Map<String, Any>
}