"use client";

import * as React from "react";
import Link from "next/link";

export function MainNav() {
  return (
    <div className="mr-4 hidden md:flex">
      <Link href="/" className="mr-6 flex items-center space-x-2">
        <span className="hidden font-bold sm:inline-block">
          Clerk Next.js + Spring Boot
        </span>
      </Link>
    </div>
  );
}
