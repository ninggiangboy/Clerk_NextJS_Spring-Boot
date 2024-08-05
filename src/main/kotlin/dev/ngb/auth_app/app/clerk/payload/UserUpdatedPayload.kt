package dev.ngb.auth_app.app.clerk.payload

import com.fasterxml.jackson.annotation.JsonProperty

data class UserUpdatedPayload(
    @JsonProperty("created_at") val createdAt: Long?,
    @JsonProperty("email_addresses") val emailAddresses: List<EmailAddress?>?,
    @JsonProperty("first_name") val firstName: String?,
    @JsonProperty("id") val id: String?,
    @JsonProperty("image_url") val imageUrl: String?,
    @JsonProperty("last_name") val lastName: String?,
    @JsonProperty("primary_email_address_id") val primaryEmailAddressId: String?,
    @JsonProperty("username") val username: String?,
) {
    val primaryEmailAddress: String?
        get() = emailAddresses?.find { it?.id == primaryEmailAddressId }?.emailAddress

    data class EmailAddress(
        @JsonProperty("email_address") val emailAddress: String?,
        @JsonProperty("id") val id: String?,
    )
}