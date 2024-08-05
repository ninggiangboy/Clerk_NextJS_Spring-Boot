'use client'

import React from 'react'

type ParsedUser = {
    id: string
    email: string | undefined
    fullName: string | null
}

export type Actor = {
    object: string
    id: string
    status: 'pending' | 'accepted' | 'revoked'
    user_id: string
    actor: object
    token: string | null
    url: string | null
    created_at: Number
    updated_at: Number
}

export default function ImpersonateUsers({ users }: { users: ParsedUser[] }) {

    return (
        <>
            <h1>Users</h1>
            <ul>
                {users?.map((userFromUserList) => {
                    return (
                        <li key={userFromUserList.id} className="flex gap-4">
                            <p>{userFromUserList.id}</p>
                            <p>{userFromUserList.fullName}</p>
                            <p>{userFromUserList?.email ? userFromUserList.email : userFromUserList.id}</p>
                        </li>
                    )
                })}
            </ul>
        </>
    )
}