package com.abernikov.multiplatformapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform