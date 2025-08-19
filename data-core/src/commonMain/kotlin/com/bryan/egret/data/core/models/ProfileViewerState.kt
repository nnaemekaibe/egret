package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericUri
import kotlinx.serialization.Serializable

@Serializable
data class ProfileViewerState(
    val following : GenericUri?,
    val followedBy : GenericUri?,
    val commonFollowersCount: Long?,
)

val ProfileViewerState?.isFollowing : Boolean get() = this?.following != null