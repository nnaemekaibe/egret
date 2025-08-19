package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.PostId
import com.bryan.egret.data.core.types.PostUri
import com.bryan.egret.data.core.types.ProfileId
import com.bryan.egret.data.core.types.RecordKey
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


@OptIn(ExperimentalTime::class)
@Serializable
data class Post(
    val cid : PostId,
    val uri: PostUri,
    val author: Profile,
    val replyCount: Long,
    val repostCount: Long,
    val likeCount: Long,
    val quoteCount: Long,
    @Contextual val indexedAt: Instant,
    val embed: Embed?,
    val quote: Post?,
    val record: Record?,
    val viewerStats: ViewerStats?,
    val labels: List<Label>
): UrlEncodableModel {
    @Serializable
    data class Record(
        val text: String,
        @Contextual val createdAt: Instant,
        val links: List<Link> = emptyList(),
        val replyRef: ReplyRef? = null,
        ): UrlEncodableModel

    @Serializable
    data class ReplyRef(
        val rootCid: PostId,
        val rootUri: PostUri,
        val parentCid: PostId,
        val parentUri: PostUri,
    )

    @Serializable
    data class ViewerStats (
        val likeUri: GenericUri?,
        val reportUri: GenericUri?,
        val threadMuted: Boolean,
        val replyDisabled: Boolean,
        val embeddingDisabled: Boolean,
        val pinned: Boolean,
    )

    @Serializable
    sealed class Create : UrlEncodableModel{
        @Serializable
        data class Metadata(
            val quote: Quote? = null,
            val reply: Reply? = null,
            val mediaFiles: List<MediaFile> = emptyList()
        )

        @Serializable
        data class Quote(
            val interaction: Interaction.Create.Repost
        ):Create(), UrlEncodableModel

        @Serializable
        data class Reply(
            val parent: Post
        ):Create(), UrlEncodableModel

        @Serializable
        data class Mention(
            val profile: Profile
        ):Create(), UrlEncodableModel

        @Serializable
        data object Timeline : Create(), UrlEncodableModel

        @Serializable
        data class Request(
            val authorId: ProfileId,
            val text: String,
            val links: List<Link>,
            val metadata: Metadata
        )
    }


    @Serializable
    sealed class Interaction {

        abstract val postId: PostId

        @Serializable
        sealed class Create : Interaction(){
            @Serializable
            data class Like(
                override val postId: PostId,
                val postUri: PostUri
            ): Create()

            @Serializable
            data class Repost(
                override val postId: PostId,
                val postUri: PostUri
            ): Create()

        }

        @Serializable
        sealed class Delete : Interaction(){
            @Serializable
            data class UnLike(
                override val postId: PostId,
                val likeUri: GenericUri
            ): Delete()

            @Serializable
            data class RemoveRepost(
                override val postId: PostId,
                val repostUri: PostUri
            ): Delete()
        }

    }

    @Serializable
    sealed class Metadata{
        @Serializable
        data class Likes(
            val profileId: ProfileId,
            val postRecordKey: RecordKey,
        ): Metadata()

        @Serializable
        data class Reposts(
            val profileId: ProfileId,
            val postRecordKey: RecordKey,
        ): Metadata()

        @Serializable
        data class Quotes(
            val profileId: ProfileId,
            val postRecordKey: RecordKey,
        ): Metadata()
    }
}


fun Post.labelVisibilitiesToDefinitions(
    labelers: List<Labeler>,
    labelPreferences: ContentLabelPreferences,
): Map<Label.Visibility, List<Label.Definition>> = labelVisibilitiesToDefinitions(
    postLabels = when{
        labels.isEmpty() -> emptySet()
        else ->  labels.mapTo(
            destination = mutableSetOf(),
            transform = Label::value
        )
    },
    labelers = labelers,
    labelsVisibilityMap = labelPreferences.associateBy(
        keySelector = ContentLabelPreference::label,
        valueTransform = ContentLabelPreference::visibility
    )
)
