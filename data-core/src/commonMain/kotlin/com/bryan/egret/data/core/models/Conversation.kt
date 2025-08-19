package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.ConversationId
import com.bryan.egret.data.core.types.Id
import kotlinx.serialization.Serializable

@Serializable
data class Conversation (
    val id : ConversationId,
    val members: List<Id.Profile>,
    val muted : Boolean,
    val unreadCount : Long,
    val lastMessage : Message?,
    val lastMessageReactedTo : Message?
)