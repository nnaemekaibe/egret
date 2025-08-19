package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericId
import com.bryan.egret.data.core.types.GenericUri
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
sealed class Notification{
    sealed class PostAssociated: Notification(){
        abstract val associatedPost : Post
    }
    
    abstract val uri : GenericUri
    abstract val cid : GenericId
    abstract val author : Profile
    abstract val isRead : Boolean
    abstract val indexedAt : Instant
    abstract val reasonSubject : GenericUri?
    
    
    data class Liked(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
        override val associatedPost: Post
    ): PostAssociated()



    data class Reposted(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
        override val associatedPost: Post
    ): PostAssociated()


    data class Followed(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
    ): Notification()



    data class Mentioned(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
        override val associatedPost: Post
    ): PostAssociated()



    data class RepliedTo(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
        override val associatedPost: Post
    ): PostAssociated()



    data class Quoted(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
        override val associatedPost: Post
    ): PostAssociated()




    data class JoinedStarterPack(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
    ): Notification()


    data class Unknown(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
    ): Notification()


    data class Verified(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
    ): Notification()


    data class Unverified(
        override val uri: GenericUri,
        override val cid: GenericId,
        override val author: Profile,
        override val isRead: Boolean,
        override val indexedAt: Instant,
        override val reasonSubject: GenericUri?,
    ): Notification()

    enum class Reason {
        Unknown,
        Like,
        Repost,
        Follow,
        Mention,
        Reply,
        Quote,
        JoinedStarterPack,
        Verified,
        Unverified,
    }
}


val Notification.associatedPostUri
    get() = when (this) {
        is Notification.Followed -> null
        is Notification.JoinedStarterPack -> null
        is Notification.Liked -> associatedPost.uri
        is Notification.Mentioned -> null
        is Notification.Quoted -> null
        is Notification.RepliedTo -> null
        is Notification.Reposted -> associatedPost.uri
        is Notification.Unknown -> null
        is Notification.Unverified -> null
        is Notification.Verified -> null
    }