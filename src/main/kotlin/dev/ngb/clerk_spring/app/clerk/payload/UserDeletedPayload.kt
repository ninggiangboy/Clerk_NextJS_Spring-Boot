package dev.ngb.clerk_spring.app.clerk.payload

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDeletedPayload(
    @JsonProperty("deleted") val deleted: Boolean,
    @JsonProperty("id") val id: String,
)