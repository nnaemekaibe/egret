package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.ProfileHandle
import com.bryan.egret.data.core.types.ProfileId
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val start: Int,
    val end: Int,
    val target: LinkTarget,
)

@Serializable
sealed interface LinkTarget{
    @Serializable
    data class UserHandleMention(
        val handle: ProfileHandle,
    ): OfProfile

    @Serializable
    data class UserDidMention(
        val did: ProfileId,
    ): OfProfile

    @Serializable
    data class ExternalLink(
        val uri: GenericUri,
    ): LinkTarget

    @Serializable
    data class Hashtag(
        val tag: String,
    ): LinkTarget


    sealed interface OfProfile : LinkTarget
}

val LinkTarget.OfProfile.path: String
    get() = when(this){
        is LinkTarget.UserDidMention -> "/profile/${did.id}"
        is LinkTarget.UserHandleMention -> "/profile/${handle.id}"
    }
