package com.bryan.egret.data.core.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
sealed interface Uri {
    val uri: String

    enum class Host(val prefix : String){
        AtProto("at://"),
        Http("http://")
    }
}


@Serializable
@JvmInline
value class ImageUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}

@Serializable
@JvmInline
value class PostUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}

@Serializable
@JvmInline
value class FeedGeneratorUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}

@Serializable
@JvmInline
value class GenericUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}


@Serializable
@JvmInline
value class StarterPackUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}



@Serializable
@JvmInline
value class ListMemberUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}



@Serializable
@JvmInline
value class ProfileUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}


@Serializable
@JvmInline
value class ListUri(
    override val uri: String
): Uri{
    override fun toString(): String = uri
}

