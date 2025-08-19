package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.ImageUri
import com.bryan.egret.data.core.types.ProfileHandle
import com.bryan.egret.data.core.types.ProfileId
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
data class Profile(
    val did: ProfileId,
    val handle: ProfileHandle,
    val displayName: String?,
    val description: String?,
    val avatar: ImageUri?,
    val banner: ImageUri?,
    val followersCount: Long?,
    val followsCount: Long?,
    val postsCount: Long?,
    val joinedViaStartPack: GenericUri?,
    @Contextual val indexedAt: Instant?,
    val createdAt: Instant?,
    val metadata : Metadata

    ): UrlEncodableModel {


        @Serializable
        sealed class Connection {
            abstract val signedInProfileId: ProfileId
            abstract val profileId: ProfileId
            abstract val followedBy: GenericUri?

            data class Follow(
                override val signedInProfileId: ProfileId,
                override val profileId: ProfileId,
                override val followedBy: GenericUri?
            ) :Connection()

            data class UnFollow(
                override val signedInProfileId: ProfileId,
                override val profileId: ProfileId,
                override val followedBy: GenericUri?,
                val followUri: GenericUri?
            ) :Connection()
        }
        @Serializable
        data class Metadata(
            val createdListCount : Long,
            val createdStartPackCount : Long,
            val createdFeedGeneratorCount: Long,
        )
    }

@Serializable
data class ProfileWithViewerState(
    val profile: Profile,
    val viewerState : ProfileViewerState
)

val Profile.contentDescription : String get() = displayName ?: handle.id


@OptIn(ExperimentalTime::class)
fun stubProfile(
    did: ProfileId,
    handle: ProfileHandle,
    displayName: String? = null,
    avatar: ImageUri? = null
) = Profile(
    did = did,
    handle = handle,
    displayName = displayName,
    description = null,
    avatar = avatar,
    banner = null,
    followersCount = null,
    followsCount = null,
    postsCount = null,
    joinedViaStartPack = null,
    indexedAt = null,
    createdAt = null,
    metadata = Profile.Metadata(
        createdListCount = 0,
        createdStartPackCount = 0,
        createdFeedGeneratorCount = 0
    )
)
