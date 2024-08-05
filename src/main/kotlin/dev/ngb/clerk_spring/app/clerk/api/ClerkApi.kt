package dev.ngb.clerk_spring.app.clerk.api

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/clerk")
interface ClerkApi {
    @RequestMapping("/webhooks")
    @PreAuthorize("permitAll")
    fun webhooks(
        @RequestBody payload: String,
        @RequestHeader headers: Map<String, String>
    ): ResponseEntity<String>
}