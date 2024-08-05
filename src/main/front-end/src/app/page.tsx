import { clerkClient } from '@clerk/nextjs/server'
import ImpersonateUsers from "@/app/components/impersonate-users";
import {whoami} from "@/lib/data";

export default async function Home() {
    const users = await clerkClient().users.getUserList()
    const info = await whoami()
    const parsedUsers = []
    for (const user of users.data) {
        parsedUsers.push({
            id: user.id,
            email: user.primaryEmailAddress?.emailAddress,
            fullName: user.fullName,
        })
    }
      return (
        <main className="flex min-h-screen flex-col items-center p-24">
            <p>Hello!</p>
            <p>
                {JSON.stringify(info, null, 4)}
            </p>
            <ImpersonateUsers users={parsedUsers} />
        </main>
      );
}
