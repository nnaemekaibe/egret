package com.bryan.egret.data.core.models

import com.bryan.egret.data.core.types.ConversationId
import com.bryan.egret.data.core.types.FeedGeneratorId
import com.bryan.egret.data.core.types.FeedGeneratorUri
import com.bryan.egret.data.core.types.GenericId
import com.bryan.egret.data.core.types.GenericUri
import com.bryan.egret.data.core.types.Id
import com.bryan.egret.data.core.types.ImageUri
import com.bryan.egret.data.core.types.ListId
import com.bryan.egret.data.core.types.ListMemberUri
import com.bryan.egret.data.core.types.ListUri
import com.bryan.egret.data.core.types.MessageId
import com.bryan.egret.data.core.types.PostId
import com.bryan.egret.data.core.types.PostUri
import com.bryan.egret.data.core.types.ProfileHandle
import com.bryan.egret.data.core.types.ProfileHandleOrId
import com.bryan.egret.data.core.types.ProfileId
import com.bryan.egret.data.core.types.ProfileUri
import com.bryan.egret.data.core.types.StarterPackId
import com.bryan.egret.data.core.types.StarterPackUri
import com.bryan.egret.data.core.types.Uri
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlin.io.encoding.Base64

sealed interface UrlEncodableModel

inline fun <reified T : UrlEncodableModel> T.toBytes() : ByteArray =
    ModelSerializerFormat.encodeToByteArray(this)

inline fun <reified T : UrlEncodableModel> T.toUrlEncodedBase64() : String =
    ModelUrlSafeBase64.encode(toBytes())

inline fun <reified T : UrlEncodableModel> String.fromBase64EncodedUrl() : ByteArray =
    ModelSerializerFormat.decodeFromByteArray(ModelUrlSafeBase64.decode(source = this))



val ModelSerializerFormat : BinaryFormat = Cbor{
    ignoreUnknownKeys = true
    serializersModule = SerializersModule{
        polymorphic(UrlEncodableModel::class){
            subclass(Profile::class)
            subclass(FeedGenerator::class)
            subclass(FeedList::class)
            subclass(StarterPack::class)
            subclass(Post::class)

            subclass(Post.Create::class)
            subclass(Post.Create.Reply::class)
            subclass(Post.Create.Quote::class)
            subclass(Post.Create.Timeline::class)
            subclass(Post.Create.Mention::class)

            subclass(Embed.Media::class)
            subclass(Video::class)
            subclass(ImageList::class)
        }

        polymorphic(Uri::class){
            subclass(ImageUri::class)
            subclass(PostUri::class)
            subclass(ProfileUri::class)
            subclass(FeedGeneratorUri::class)
            subclass(GenericUri::class)
            subclass(ListUri::class)
            subclass(StarterPackUri::class)
            subclass(ListMemberUri::class)
        }

        polymorphic(Id::class){
            subclass(ProfileId::class)
            subclass(ProfileHandle::class)
            subclass(ProfileHandleOrId::class)
            subclass(PostId::class)
            subclass(FeedGeneratorId::class)
            subclass(ListId::class)
            subclass(StarterPackId::class)
            subclass(ConversationId::class)
            subclass(MessageId::class)
            subclass(GenericId::class)
        }

        polymorphic(Id.Profile::class){
            subclass(ProfileId::class)
            subclass(ProfileHandle::class)
            subclass(ProfileHandleOrId::class)
        }

        polymorphic(Embed::class){
            subclass(UnknownEmbed::class)
            subclass(Video::class)
            subclass(ExternalEmbed::class)
            subclass(ImageList::class)
        }


        polymorphic(Embed.Media::class){
            subclass(Video::class)
            subclass(ImageList::class)
        }


        polymorphic(LinkTarget::class){
            subclass(LinkTarget.OfProfile::class)
            subclass(LinkTarget.UserDidMention::class)
            subclass(LinkTarget.UserHandleMention::class)
            subclass(LinkTarget.ExternalLink::class)
            subclass(LinkTarget.Hashtag::class)
        }

        polymorphic(LinkTarget.OfProfile::class){
            subclass(LinkTarget.UserHandleMention::class)
            subclass(LinkTarget.UserDidMention::class)
        }


        polymorphic(Post.Create::class){
            subclass(Post.Create.Reply::class)
            subclass(Post.Create.Quote::class)
            subclass(Post.Create.Timeline::class)
            subclass(Post.Create.Mention::class)
        }

        polymorphic(MediaFile::class){
            subclass(MediaFile.Photo::class)
            subclass(MediaFile.Video::class)
        }

    }
}

val ModelUrlSafeBase64 = Base64.UrlSafe.withPadding(
    option = Base64.PaddingOption.ABSENT
)