package com.jefferson.thesportsdb.client.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class TheSportsDBV1LeaguesDto(
    @Json(name = "leagues")
    val leagues: List<TheSportsDBV1LeagueDto>? = null
)
