package com.bryan.egret.data.core.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline


@Serializable
@JvmInline
value class RecordKey(
    val value: String
)

val PostUri.recordKey
    get() = RecordKey(uri.split("/").last())

val GenericUri.recordKey
    get() = RecordKey(uri.split("/").last())