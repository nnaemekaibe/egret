package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.ImageUri
import kotlinx.serialization.Serializable


@Serializable
data class ExternalEmbed(
    val uri : GenericUri,
    val title: String,
    val description: String,
    val thumb: ImageUri,
): Embed
