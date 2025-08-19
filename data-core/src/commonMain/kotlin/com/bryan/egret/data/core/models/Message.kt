package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.ConversationId
import com.bryan.egret.data.core.types.MessageId
import com.bryan.egret.data.core.types.ProfileId
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
data class Message (
    val id : MessageId?,
    val conversationId: ConversationId,
    val text: String,
    val sender: Profile,
    val isDeleted: Boolean,
    @Contextual val sentAt: Instant,
    val feedGenerator: FeedGenerator?,
    val list: FeedList?,
    val starterPark: StarterPack?,
    val post: Post?,
    val reactions: List<Reaction>

){

    @Serializable
    data class Reaction(
        val senderId: ProfileId,
        val value: String,
        @Contextual val createdAt: Instant
    )

    @Serializable
    data class Create(
        val conversationId: ConversationId,
        val text: String,
        val links : List<Link>
    )

    @Serializable
    sealed class UpdateReaction {
        abstract val convoId: ConversationId
        abstract val messageId: MessageId
        abstract val value: String

        @Serializable
        data class Add(
            override val convoId: ConversationId,
            override val messageId: MessageId,
            override val value: String
        ): UpdateReaction()

        @Serializable
        data class Remove(
            override val convoId: ConversationId,
            override val messageId: MessageId,
            override val value: String
        ): UpdateReaction()


    }

}