package com.jefferson.bestfdjtest.sportsleagues.domain.model

data class Team(
    val id: String = "",
    val name: String = "",
    // some teams don't have any badge
    val badgeImageUrl: String? = null,
    val leaguesNames: List<String> = emptyList(),
)
