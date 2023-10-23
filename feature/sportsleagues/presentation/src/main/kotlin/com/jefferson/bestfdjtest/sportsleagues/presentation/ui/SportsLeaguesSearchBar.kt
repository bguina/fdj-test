package com.jefferson.bestfdjtest.sportsleagues.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.presentation.ext.sportIcon
import com.jefferson.bestfdjtest.style.R
import com.jefferson.bestfdjtest.style.theme.FdjTestTheme

@Preview
@Composable
private fun Preview() = FdjTestTheme {
    SportsLeaguesSearchBar.Layout(
        leagueSuggestions = SportsLeaguesPlaceHolders.leagues,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
object SportsLeaguesSearchBar {

    @Composable
    operator fun invoke(
        selectedLeague: League?,
        leagueSuggestions: List<League> = emptyList(),
        onQueryChanged: (String) -> Unit = {},
        onLeagueClicked: (League?) -> Unit = {},
    ) {
        Layout(
            selectedLeague = selectedLeague,
            leagueSuggestions = leagueSuggestions,
            onQueryChanged = onQueryChanged,
            onLeagueClicked = onLeagueClicked,
        )
    }

    @Composable
    fun Layout(
        selectedLeague: League? = null,
        leagueSuggestions: List<League> = emptyList(),
        onQueryChanged: (String) -> Unit = {},
        onLeagueClicked: (League?) -> Unit = {},
    ) {
        var active by rememberSaveable { mutableStateOf(leagueSuggestions.isNotEmpty()) }
        val query = rememberSaveable { mutableStateOf("") }

        LaunchedEffect(Unit) { onQueryChanged(query.value) }
        BackHandler(query.value.isNotEmpty()) {
            query.value = ""
            onQueryChanged(query.value)
            active = false
        }
        SearchBar(
            modifier = Modifier,
            query = query.value,
            onQueryChange = {
                query.value = it
                onQueryChanged(query.value)
            },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(stringResource(R.string.sportsleagues_search_hint)) },
            leadingIcon = {
                Icon(
                    imageVector = selectedLeague?.sportIcon ?: Icons.Default.Search,
                    contentDescription = null,
                )
            },
            trailingIcon = query.value.takeUnless(String::isEmpty)?.let {
                {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                query.value = ""
                                onQueryChanged(query.value)
                                active = true
                            },
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.sportsleagues_clear_content_description)
                    )
                }
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(leagueSuggestions) { league ->
                    ListItem(
                        headlineContent = { Text(league.name) },
                        supportingContent = { Text(league.sport) },
                        leadingContent = {
                            Icon(
                                imageVector = league.sportIcon,
                                contentDescription = league.sport
                            )
                        },
                        modifier = Modifier.clickable {
                            active = false
                            query.value = league.name
                            onQueryChanged(query.value)
                            onLeagueClicked(league)
                        }
                    )
                }
            }
        }
    }
}
