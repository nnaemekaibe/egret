package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericId
import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.ImageUri
import kotlinx.serialization.Serializable


@Serializable
data class Video(
    val cid: GenericId,
    val playlist: GenericUri,
    val thumbnail: ImageUri? = null,
    val alt: String? = null,
    override val width: Long?,
    override val height: Long?,
): Embed.Media, AspectRatio