package dev.ngb.auth_app.constant

interface CommonConstant {
    companion object {
        const val CLERK_CREATE_TYPE = "user.created"
        const val CLERK_DELETE_TYPE = "user.deleted"
        const val CLERK_UPDATE_TYPE = "user.updated"
    }
}