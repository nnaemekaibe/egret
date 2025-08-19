package com.bryan.egret.data.core.models

import kotlinx.serialization.Serializable

data class CursorList<out T>(
    val items: List<T>,
    val nextCursor: Cursor,

): List<T> by items

inline fun <T, R> CursorList<T>.mapCursorList(
    mapper: (T) -> R
) = CursorList(
    items = items.map(mapper),
    nextCursor = nextCursor
)


@Serializable
sealed class Cursor {


    @Serializable
    data object Initial : Cursor()


    @Serializable
    data object Pending : Cursor()

    @Serializable
    data class Next(
        val cursor: String
    ) : Cursor()
}

val Cursor.value
    get() = when(this){
        Cursor.Initial -> null
        Cursor.Pending -> throw IllegalStateException("Pending cursors cannot be used to fetch data")
        is Cursor.Next -> cursor
    }