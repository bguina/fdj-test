package com.jefferson.bestfdjtest.sportsleagues.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefferson.bestfdjtest.errors.domain.model.TechnicalError
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.GetLeagueByIdUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.ListTeamsByLeagueIdUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.ObserveLeagueSuggestionsUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.ScheduleAndObserveWorkersUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.presentation.ext.savedLeagueId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SportsLeaguesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val scheduleAndObserveWorkersUseCase: ScheduleAndObserveWorkersUseCase,
    private val getLeagueByIdUseCase: GetLeagueByIdUseCase,
    private val observeLeagueSuggestionsUseCase: ObserveLeagueSuggestionsUseCase,
    private val listTeamsByLeagueIdUseCase: ListTeamsByLeagueIdUseCase,
) : ViewModel() {

    private var observeSuggestionsJob: Job? = null

    private var loadLeagueJob: Job? = null

    private val mutableUiState: MutableStateFlow<SportsLeaguesUiState> = MutableStateFlow(
        SportsLeaguesUiState()
    )

    val uiState: StateFlow<SportsLeaguesUiState> = mutableUiState.asStateFlow()

    init {
        observeSyncState()
        restoreLeagueFromId(savedStateHandle.savedLeagueId)
    }

    fun onQueryChanged(query: String?) {
        observeSuggestionsJob?.cancel()
        mutableUiState.update {
            it.copy(
                selectedLeague = null,
                teams = null,
            )
        }
        observeSuggestionsJob = viewModelScope.launch {
            observeLeagueSuggestionsUseCase(query = query.orEmpty()).collect { suggestions ->
                mutableUiState.update {
                    it.copy(
                        leagueSuggestions = suggestions,
                    )
                }
            }
        }
    }

    fun onLeagueClicked(league: League?) {
        loadLeagueJob?.cancel()
        savedStateHandle.savedLeagueId = league?.id
        mutableUiState.update {
            it.copy(
                isLoading = true,
                selectedLeague = league,
                teams = null,
                error = null,
            )
        }
        loadLeagueJob = viewModelScope.launch {
            try {
                val teams = league?.id?.let { id -> listTeamsByLeagueIdUseCase(id) }

                mutableUiState.update {
                    it.copy(
                        isLoading = false,
                        teams = teams,
                    )
                }

            } catch (e: TechnicalError) {
                mutableUiState.update {
                    it.copy(
                        isLoading = false,
                        error = e,
                    )
                }
            }
        }
    }

    private fun observeSyncState() {
        viewModelScope.launch {
            scheduleAndObserveWorkersUseCase().collect { isSyncing ->
                mutableUiState.update { it.copy(isSyncing = isSyncing) }
            }
        }
    }

    private fun restoreLeagueFromId(leagueId: String?) {
        viewModelScope.launch {
            val league = leagueId?.let { leagueId -> getLeagueByIdUseCase.invoke(leagueId) }
            onLeagueClicked(league)
        }
    }

    companion object {
        private const val TAG: String = "LeaguesSearchViewModel"
    }
}
