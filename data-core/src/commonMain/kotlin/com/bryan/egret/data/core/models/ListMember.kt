package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.ListMemberUri
import com.bryan.egret.data.core.types.ListUri
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
@OptIn(ExperimentalTime::class)
data class ListMember(
    val uri: ListMemberUri,
    val subject: Profile,
    val listUri: ListUri,
    val viewerState: ProfileViewerState?,
    @Contextual val createdAt: Instant
)