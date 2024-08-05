package dev.ngb.auth_app.app.clerk.payload

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDeletedPayload(
    @JsonProperty("deleted") val deleted: Boolean,
    @JsonProperty("id") val id: String,
)