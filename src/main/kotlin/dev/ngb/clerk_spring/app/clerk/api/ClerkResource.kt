package dev.ngb.clerk_spring.app.clerk.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.ngb.clerk_spring.app.clerk.payload.UserCreatedPayload
import dev.ngb.clerk_spring.app.clerk.payload.UserDeletedPayload
import dev.ngb.clerk_spring.constant.CommonConstant.ClerkEventType.Companion.CLERK_USER_CREATED
import dev.ngb.clerk_spring.constant.CommonConstant.ClerkEventType.Companion.CLERK_USER_DELETED
import dev.ngb.clerk_spring.constant.CommonConstant.ClerkEventType.Companion.CLERK_USER_UPDATED
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.svix.kotlin.Webhook
import dev.ngb.clerk_spring.config.property.ClerkWebhooksProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import java.net.http.HttpHeaders

@RestController
class ClerkResource(private val properties: ClerkWebhooksProperties) : ClerkApi {
    override fun webhooks(
        @RequestBody payload: String,
        @RequestHeader headers: Map<String, String>
    ): ResponseEntity<String> {
        try {
            val webhook = Webhook(properties.secret)
            val httpHeaders = HttpHeaders.of(headers.mapValues { listOf(it.value) }) { _, _ -> true }
            webhook.verify(payload, httpHeaders)
            val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val json = mapper.readTree(payload)
            when (json["type"].asText()) {
                CLERK_USER_CREATED -> {
                    val userCreatedPayload = mapper.readValue(json["data"].traverse(), UserCreatedPayload::class.java)
                    println(userCreatedPayload)
                }

                CLERK_USER_UPDATED -> {
                    val userUpdatedPayload = mapper.readValue(json["data"].traverse(), UserCreatedPayload::class.java)
                    println(userUpdatedPayload)
                }

                CLERK_USER_DELETED -> {
                    val userDeletedPayload = mapper.readValue(json["data"].traverse(), UserDeletedPayload::class.java)
                    println(userDeletedPayload)
                }

                else -> {
                    println("Unknown object type")
                }
            }
            return ResponseEntity.ok().build()
        } catch (e: Exception) {
            println(e)
            return ResponseEntity.badRequest().build()
        }
    }
}