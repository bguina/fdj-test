package com.jefferson.bestfdjtest.sportsleagues.presentation.ui

import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team

object SportsLeaguesPlaceHolders {
    val leagues: List<League> = listOf(
        League(
            id = "0",
            sport = "Rugby",
            name = "Austalian Shute Shield",
            altName = "",
        ),
        League(
            id = "0",
            sport = "Soccer",
            name = "Coupe de France",
            altName = "French Cup",
        ),
    )

    val teams: List<Team> = listOf(
        Team(
            id = "0",
            name = "Team 1",
            badgeImageUrl = "",
            leaguesNames = emptyList()
        ),
        Team(
            id = "1",
            name = "Team 2",
            badgeImageUrl = "",
            leaguesNames = emptyList()
        ),
    )
}
