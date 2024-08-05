package dev.ngb.auth_app.config.property

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ClerkWebhooksProperties::class)
class PropertiesConfig