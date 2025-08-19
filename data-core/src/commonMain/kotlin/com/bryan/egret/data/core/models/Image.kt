package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.ImageUri
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val thumb: ImageUri,
    val fullsize: ImageUri,
    val alt: String,
    override val width: Long?,
    override val height: Long?
): AspectRatio


@Serializable
data class ImageList(
    val images: List<Image>
): Embed.Media
