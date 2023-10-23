package com.jefferson.thesportsdb.client.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class TheSportsDBV1TeamsDto(
    @Json(name = "teams")
    val teams: List<TheSportsDBV1TeamDto>? = null
)
