package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.ProfileId
import kotlinx.serialization.Serializable

typealias ContentLabelPreferences = List<ContentLabelPreference>


@Serializable
data class Preferences(
    val timelinePreferences: List<TimelinePreference>,
    val contentLabelPreferences: ContentLabelPreferences = emptyList()
): UrlEncodableModel{
    companion object{
        val DefaultPreferences = Preferences(
            timelinePreferences = listOf(
                TimelinePreference(
                    id = Constants.discoveryFeed.uri,
                    type = "",
                    pinned = true,
                    value = Constants.discoveryFeed.uri
                )
            ),
            contentLabelPreferences = emptyList()
        )
    }
}


@Serializable
data class TimelinePreference(
    val id: String,
    val type: String,
    val pinned: Boolean,
    val value: String
)

@Serializable
data class ContentLabelPreference(
    val labelerId: ProfileId?,
    val label: Label.Value,
    val visibility: Label.Visibility
)