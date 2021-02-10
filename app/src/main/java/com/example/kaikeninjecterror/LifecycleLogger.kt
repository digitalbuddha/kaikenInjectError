package com.example.kaikeninjecterror

interface LifecycleLogger {
    fun log() {}
}

object RealLifecycleLogger : LifecycleLogger