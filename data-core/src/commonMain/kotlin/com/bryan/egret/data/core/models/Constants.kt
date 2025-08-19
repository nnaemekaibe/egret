package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.FeedGeneratorUri
import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.Id
import com.bryan.egret.data.core.types.PostId
import com.bryan.egret.data.core.types.PostUri
import com.bryan.egret.data.core.types.ProfileHandle
import com.bryan.egret.data.core.types.ProfileId

object Constants {
    const val UNKNOWN = "at://unknown"
    val timelineFeed = GenericUri("at://self")
    val discoveryFeed = FeedGeneratorUri("at://did:plc:z72i7hdynmk6r22z27h6tvur/app.bsky.feed.generator/whats-hot")
    val blockedPostId = PostId("at://blocked")
    val notFoundPostId = PostId("at://not_found")
    val unknownPostId = PostId(UNKNOWN)
    val unknownAuthorId = ProfileId("did:web:egret.app.unknown.user")
    val unknownPostUri = PostUri(UNKNOWN)
    val unknownAuthorHandle = ProfileHandle(UNKNOWN)
    val unknownGenericUri = GenericUri(UNKNOWN)
}