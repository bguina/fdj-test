package com.jefferson.bestfdjtest.sportsleagues.presentation.ui

import com.jefferson.bestfdjtest.errors.domain.model.TechnicalError
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team

data class SportsLeaguesUiState(
    val isSyncing: Boolean = false,
    val isLoading: Boolean = false,
    val selectedLeague: League? = null,
    val leagueSuggestions: List<League> = emptyList(),
    val teams: List<Team>? = null,
    val error: TechnicalError? = null,
)
