package com.bryan.egret.data.core.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


@OptIn(ExperimentalTime::class)
@Serializable
data class Trends(
    val topic: String,
    val status: Status?,
    val displayName: String?,
    val link: String,
    @Contextual val startedAt: Instant,
    val postCount: Long,
    val category: String? = null,
    val actions: List<Profile>

){
    enum class Status{
        Hot
    }
}