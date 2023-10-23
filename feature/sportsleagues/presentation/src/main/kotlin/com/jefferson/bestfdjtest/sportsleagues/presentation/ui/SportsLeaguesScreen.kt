package com.jefferson.bestfdjtest.sportsleagues.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.splashscreen.SplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.jefferson.bestfdjtest.errors.presentation.ui.FdjErrorText
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team
import com.jefferson.bestfdjtest.style.R
import com.jefferson.bestfdjtest.style.theme.FdjTestTheme

@Preview
@Composable
private fun Preview() = FdjTestTheme {
    SportsLeaguesScreen.Layout(
        teams = SportsLeaguesPlaceHolders.teams,
    )
}

object SportsLeaguesScreen {

    @Composable
    operator fun invoke(
        viewModel: SportsLeaguesViewModel = hiltViewModel(),
        splashScreen: SplashScreen,
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle(SportsLeaguesUiState())

        LaunchedEffect(state.isSyncing) {
            splashScreen.setKeepOnScreenCondition { state.isSyncing }
        }
        Layout(
            selectedLeague = state.selectedLeague,
            isLoading = state.isLoading,
            leagueSuggestions = state.leagueSuggestions,
            teams = state.teams,
            error = state.error,
            onQueryChanged = viewModel::onQueryChanged,
            onLeagueClicked = viewModel::onLeagueClicked,
        )
    }

    @Composable
    fun Layout(
        selectedLeague: League? = null,
        isLoading: Boolean = true,
        leagueSuggestions: List<League> = emptyList(),
        teams: List<Team>? = null,
        error: Throwable? = null,
        onQueryChanged: (String) -> Unit = {},
        onLeagueClicked: (League?) -> Unit = {},
    ) {
        Scaffold(
            topBar = {
                SearchTopBar(
                    selectedLeague = selectedLeague,
                    leagueSuggestions = leagueSuggestions,
                    onQueryChanged = onQueryChanged,
                    onLeagueClicked = onLeagueClicked,
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                when {
                    null != error -> {
                        FdjErrorText(
                            e = error,
                        )
                    }

                    isLoading -> {
                        CircularProgressIndicator()
                    }

                    null == teams -> {
                        // leave blank
                    }

                    else -> {
                        TeamBadgesGrid(
                            modifier = Modifier
                                .fillMaxSize(),
                            teams = teams,
                        )
                    }
                }
            }

        }
    }

    @Composable
    fun SearchTopBar(
        selectedLeague: League?,
        leagueSuggestions: List<League> = emptyList(),
        onQueryChanged: (String) -> Unit = {},
        onLeagueClicked: (League?) -> Unit = {},
    ) {
        Column(
            modifier = Modifier
                .semantics { isContainer = true }
                .zIndex(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SportsLeaguesSearchBar(
                selectedLeague = selectedLeague,
                leagueSuggestions = leagueSuggestions,
                onQueryChanged = onQueryChanged,
                onLeagueClicked = onLeagueClicked,
            )
        }
    }

    @Composable
    fun TeamBadgesGrid(
        modifier: Modifier,
        teams: List<Team> = emptyList(),
    ) {
        if (teams.isNotEmpty()) {
            val lazyListState = rememberLazyGridState()

            LazyVerticalGrid(
                modifier = modifier,
                state = lazyListState,
                columns = GridCells.Fixed(2),
            ) {
                items(teams) { team ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (null != team.badgeImageUrl) {
                            AsyncImage(
                                model = team.badgeImageUrl,
                                contentDescription = team.name,
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp, 32.dp),
                                text = stringResource(
                                    R.string.sportsleagues_team_no_badge,
                                    team.name
                                ),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                    }
                }
            }
        } else {
            Text(
                text = stringResource(R.string.sportsleagues_no_team_found),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
