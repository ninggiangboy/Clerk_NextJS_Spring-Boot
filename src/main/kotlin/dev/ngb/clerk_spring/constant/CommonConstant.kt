package dev.ngb.clerk_spring.constant

interface CommonConstant {
    interface ClerkEventType {
        companion object {
            const val CLERK_USER_CREATED = "user.created"
            const val CLERK_USER_DELETED = "user.deleted"
            const val CLERK_USER_UPDATED = "user.updated"
        }
    }
}