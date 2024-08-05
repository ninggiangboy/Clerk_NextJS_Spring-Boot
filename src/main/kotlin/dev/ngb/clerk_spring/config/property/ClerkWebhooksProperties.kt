package dev.ngb.clerk_spring.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "clerk.webhooks")
data class ClerkWebhooksProperties(
    val secret: String,
)
