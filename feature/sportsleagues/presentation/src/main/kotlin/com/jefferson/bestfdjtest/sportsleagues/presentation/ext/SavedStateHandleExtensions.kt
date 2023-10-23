package com.jefferson.bestfdjtest.sportsleagues.presentation.ext

import androidx.lifecycle.SavedStateHandle

private const val SAVED_STATE_KEY_LEAGUE_ID: String = "league_id"

var SavedStateHandle.savedLeagueId: String?
    get() = this[SAVED_STATE_KEY_LEAGUE_ID]
    set(value) {
        this[SAVED_STATE_KEY_LEAGUE_ID] = value
    }
