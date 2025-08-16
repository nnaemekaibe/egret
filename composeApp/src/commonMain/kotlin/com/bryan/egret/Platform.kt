package com.bryan.egret

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform