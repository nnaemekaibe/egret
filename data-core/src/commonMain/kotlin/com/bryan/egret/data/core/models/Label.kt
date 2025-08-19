package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.ProfileId
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class Label @OptIn(ExperimentalTime::class) constructor(
    val uri: GenericUri?,
    val creatorId: ProfileId,
    val value: Value,
    val version: Long,
    @Contextual val createdAt: Instant,
){
    @Serializable
    @JvmInline
    value class Value(
        val name: String,
    )

    @Serializable
    data class Visibility(
        val value : String
    ){
        companion object{
            val Hide = Visibility("hide")
            val Show = Visibility("show")
            val Warn = Visibility("warn")
        }
    }

    @Serializable
    data class Definition(
        val adultOnly: Boolean,
        val blurs: BlurTarget,
        val defaultSetting: Visibility,
        val identifier : Value,
        val severity: Severity,
    )

    enum class BlurTarget{
        Content,
        Media,
        None
    }

    enum class Severity{
        Alert,
        Inform,
        None,
    }

    companion object{
        val hidden = Value("!hide")
        val nonAuthenticated = Value("!no-unauthenticated")
    }
}

@Serializable
data class Labeler(
    val uri: GenericUri,
    val creatorId: ProfileId,
    val definitions: List<Label.Definition>,
    val values : List<Label.Value>,
)

typealias Labelers = List<Labeler>

fun labelVisibilitiesToDefinitions(
    postLabels: Set<Label.Value>,
    labelers: Labelers,
    labelsVisibilityMap: Map<Label.Value, Label.Visibility>
): Map<Label.Visibility, List<Label.Definition>> = when{
    postLabels.isEmpty() -> emptyMap()
    else -> labelers.fold(
        mutableMapOf<Label.Visibility, MutableList<Label.Definition>>()
    ){ destination, labeler ->
        labeler.definitions.fold(destination) innerFold@{ innerDestination, definition ->
            if(!postLabels.contains(definition.identifier)) return@innerFold innerDestination

            val mayBlur = definition.adultOnly
                    || definition.blurs == Label.BlurTarget.Media
                    || definition.blurs == Label.BlurTarget.Content

            if(!mayBlur) return@innerFold innerDestination

            val visibility = labelsVisibilityMap[definition.identifier]
                ?: definition.defaultSetting

            innerDestination.getOrPut(visibility){
                mutableListOf()
            }.add(definition)

            innerDestination

        }

    }
}