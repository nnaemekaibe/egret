import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface CursorQuery{
    val data: Data

    @OptIn(ExperimentalTime::class)
    @Serializable
    data class Data(
        val page: Int,
        @Contextual val cursorAnchor: Instant,
        val limit: Long = 30L
    )
}

val CursorQuery.Data.offset
    get() = page * limit
