package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.ImageUri
import com.bryan.egret.data.core.types.ListId
import com.bryan.egret.data.core.types.ListUri
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
data class FeedList(
    val cid: ListId,
    val uri: ListUri,
    val avatar: ImageUri?,
    val listItemCount: Long?,
    val creator: Profile,
    @Contextual val indexedAt: Instant,
    val name: String,
    val purpose: String,
    val description: String?,
    val labels: List<Label>
    ): UrlEncodableModel
