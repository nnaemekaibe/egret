package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.StarterPackId
import com.bryan.egret.data.core.types.StarterPackUri
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
data class StarterPack(
    val cid: StarterPackId,
    val uri: StarterPackUri,
    val creator: Profile,
    val name: String,
    val description: String?,
    val list: FeedList?,
    val joinedWeekCount: Long?,
    val joinedAllTimeCount: Long?,
    @Contextual val indexedAt : Instant,
    val labels: List<Label>
): UrlEncodableModel
