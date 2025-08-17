package com.bryan.egret.data.core.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline


sealed interface Id {
    val id: String

    sealed interface Profile : Id
}

@Serializable
@JvmInline
value class PostId(
    override val id: String
): Id{
    override fun toString(): String = id
}

@Serializable
@JvmInline
value class ProfileId(
    override val id: String
): Id.Profile{
    override fun toString(): String = id
}

@Serializable
@JvmInline
value class ProfileHandle(
    override val id: String
): Id.Profile{
    override fun toString(): String = id
}