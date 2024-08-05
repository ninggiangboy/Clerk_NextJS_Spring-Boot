package dev.ngb.auth_app.app.clerk.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.ngb.auth_app.app.clerk.payload.UserCreatedPayload
import dev.ngb.auth_app.app.clerk.payload.UserDeletedPayload
import dev.ngb.auth_app.constant.CommonConstant.Companion.CLERK_CREATE_TYPE
import dev.ngb.auth_app.constant.CommonConstant.Companion.CLERK_DELETE_TYPE
import dev.ngb.auth_app.constant.CommonConstant.Companion.CLERK_UPDATE_TYPE
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.svix.kotlin.Webhook
import dev.ngb.auth_app.config.property.ClerkWebhooksProperties
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
                CLERK_CREATE_TYPE -> {
                    val userCreatedPayload = mapper.readValue(json["data"].traverse(), UserCreatedPayload::class.java)
                    println(userCreatedPayload)
                }

                CLERK_UPDATE_TYPE -> {
                    val userUpdatedPayload = mapper.readValue(json["data"].traverse(), UserCreatedPayload::class.java)
                    println(userUpdatedPayload)
                }

                CLERK_DELETE_TYPE -> {
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