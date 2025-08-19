package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.FeedGeneratorId
import com.bryan.egret.data.core.types.FeedGeneratorUri
import com.bryan.egret.data.core.types.ImageUri
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class FeedGenerator @OptIn(ExperimentalTime::class) constructor(
    val cid: FeedGeneratorId,
    val did: FeedGeneratorId,
    val uri: FeedGeneratorUri,
    val avatar: ImageUri?,
    val likeCount: Long?,
    val creator: Profile,
    val displayName: String,
    val description: String?,
    val contentMode: String?,
    val acceptsInteractions: Boolean?,
    @Contextual val indexedAt: Instant,
    val labels: List<Label>

    ): UrlEncodableModel{
        enum class Status{
            Pinned,
            Saved,
            None
        }
    }
