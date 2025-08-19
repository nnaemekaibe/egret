package com.bryan.egret.data.core.models

import kotlinx.serialization.Serializable


sealed interface Embed{
    @Serializable
    sealed interface Media : Embed, UrlEncodableModel
}


@Serializable
sealed class MediaFile : UrlEncodableModel{
    abstract val data: ByteArray
    abstract val width: Long
    abstract val height: Long


    @Serializable
    class Photo(
        override val data: ByteArray,
        override val width: Long,
        override val height: Long,
        val altText: String? = ""
    ): MediaFile()

    @Serializable
    class Video(
        override val data: ByteArray,
        override val width: Long,
        override val height: Long,
        val altText: String? = ""
    ): MediaFile()
}

sealed interface AspectRatio{
    val width: Long?
    val height: Long?
}

val AspectRatio.aspectRatioOrSquare: Float
    get() = withWidthAndHeight(1f){ width, height ->
        width.toFloat() / height
    }

val AspectRatio.isLandscape: Boolean
    get() = withWidthAndHeight(false){ width, height ->
        width > height
    }



private inline fun <T> AspectRatio.withWidthAndHeight(
    default: T,
    crossinline block :(Long, Long) -> T,
) : T {
    val currentWidth = width ?: return default
    val currentHeight = height ?: return default

    return block(currentWidth, currentHeight)
}

@Serializable
data object UnknownEmbed : Embed