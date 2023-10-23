package com.jefferson.bestfdjtest.sportsleagues.presentation.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.GetLeagueByIdUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.ListTeamsByLeagueIdUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.ObserveLeagueSuggestionsUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.interactor.ScheduleAndObserveWorkersUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team
import com.jefferson.bestfdjtest.sportsleagues.presentation.ext.savedLeagueId
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LeaguesSearchViewModelTests {

    @MockK
    private lateinit var scheduleAndObserveWorkersUseCase: ScheduleAndObserveWorkersUseCase

    @MockK
    private lateinit var getLeagueByIdUseCase: GetLeagueByIdUseCase

    @MockK
    private lateinit var observeLeagueSuggestionsUseCase: ObserveLeagueSuggestionsUseCase

    @MockK
    private lateinit var listTeamsByLeagueIdUseCase: ListTeamsByLeagueIdUseCase

    private fun newSubject(
        savedStateHandle: SavedStateHandle = SavedStateHandle()
    ): SportsLeaguesViewModel = SportsLeaguesViewModel(
        savedStateHandle = savedStateHandle,
        scheduleAndObserveWorkersUseCase = scheduleAndObserveWorkersUseCase,
        getLeagueByIdUseCase = getLeagueByIdUseCase,
        observeLeagueSuggestionsUseCase = observeLeagueSuggestionsUseCase,
        listTeamsByLeagueIdUseCase = listTeamsByLeagueIdUseCase,
    )

    private fun newSubject(
        restoredLeagueId: String,
    ): SportsLeaguesViewModel = newSubject(
        savedStateHandle = SavedStateHandle().apply {
            savedLeagueId = restoredLeagueId
        }
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when search query changes, it should load suggestions`() {
        runTest {
            // Given
            val newQuery = "AA"
            val suggestions = listOf(
                League(),
                League(),
                League(),
            )
            every { scheduleAndObserveWorkersUseCase.invoke() } returns flowOf(false)
            coEvery { observeLeagueSuggestionsUseCase(newQuery) } returns flowOf(suggestions)

            // When
            val subject = newSubject()
            subject.onQueryChanged(newQuery)

            // Then
            subject.uiState.test {
                val uiState = awaitItem()
                assertThat(uiState.selectedLeague, `is`(nullValue()))
                assertThat(uiState.leagueSuggestions, `is`(suggestions))
            }
        }
    }

    @Test
    fun `when search query changes, it should clear the current team list`() {
        runTest {
            // Given
            val newQuery = "AA"
            val suggestions = listOf(
                League(),
                League(),
                League(),
            )
            val league = League(id = "1")
            val teams = listOf(
                Team(),
                Team(),
                Team(),
            )
            every { scheduleAndObserveWorkersUseCase.invoke() } returns flowOf(false)
            coEvery { observeLeagueSuggestionsUseCase(newQuery) } returns flowOf(suggestions)
            coEvery { getLeagueByIdUseCase(league.id) } returns league
            coEvery { listTeamsByLeagueIdUseCase(league.id) } returns teams

            val subject = newSubject()
            subject.onLeagueClicked(league)

            // When
            subject.uiState.test {
                awaitItem()
                subject.onQueryChanged(newQuery)
                awaitItem()

                // Then
                val uiState = awaitItem()
                assertThat(uiState.selectedLeague, `is`(nullValue()))
                assertThat(uiState.teams, `is`(nullValue()))
            }
        }
    }

    @Test
    fun `when a league suggestion is clicked, it should load the team list`() {
        runTest {
            // Given
            val savedStateHandle = SavedStateHandle()
            val league = League(id = "1")
            val teams = listOf(
                Team(),
                Team(),
                Team(),
            )
            every { scheduleAndObserveWorkersUseCase.invoke() } returns flowOf(false)
            coEvery { getLeagueByIdUseCase(league.id) } returns league
            coEvery { listTeamsByLeagueIdUseCase(league.id) } returns teams

            savedStateHandle.savedLeagueId = league.id

            // When
            val subject = newSubject(savedStateHandle)
            subject.uiState.test {
                awaitItem()
                subject.onLeagueClicked(league)
                val loadingState = awaitItem()
                assertThat(loadingState.isLoading, `is`(true))

                // Then
                val resultState = awaitItem()
                assertThat(resultState.selectedLeague, `is`(league))
                assertThat(resultState.teams, `is`(teams))
            }
        }
    }

    @Test
    fun `when a league suggestion is clicked, its id must be saved`() {
        runTest {
            // Given
            val savedStateHandle = SavedStateHandle()
            val league = League(id = "1")
            val teams = listOf(
                Team(),
                Team(),
                Team(),
            )
            savedStateHandle.savedLeagueId = null
            every { scheduleAndObserveWorkersUseCase.invoke() } returns flowOf(false)
            coEvery { listTeamsByLeagueIdUseCase(league.id) } returns teams

            // When
            val subject = newSubject(savedStateHandle)
            subject.onLeagueClicked(league)

            // Then
            assertThat(savedStateHandle.savedLeagueId, `is`(league.id))
        }
    }

    @Test
    fun `when league is cleared, team list should be cleared`() {
        runTest {
            // Given
            val league = League(id = "1")
            val teams = listOf(
                Team(),
                Team(),
                Team(),
            )
            every { scheduleAndObserveWorkersUseCase.invoke() } returns flowOf(false)
            coEvery { getLeagueByIdUseCase(league.id) } returns league
            coEvery { listTeamsByLeagueIdUseCase(league.id) } returns teams

            // When
            val subject = newSubject(league.id)
            subject.onLeagueClicked(null)

            // Then
            subject.uiState.test {
                val uiState = awaitItem()
                assertThat(uiState.selectedLeague, `is`(nullValue()))
                assertThat(uiState.teams, `is`(nullValue()))
            }
        }
    }

    @Test
    fun `when league is cleared, saved league id must also be cleared`() {
        runTest {
            // Given
            val savedStateHandle = SavedStateHandle()
            val league = League(id = "1")
            val teams = listOf(
                Team(),
                Team(),
                Team(),
            )
            every { scheduleAndObserveWorkersUseCase.invoke() } returns flowOf(false)
            coEvery { getLeagueByIdUseCase(league.id) } returns league
            coEvery { listTeamsByLeagueIdUseCase(league.id) } returns teams
            savedStateHandle.savedLeagueId = league.id

            // When
            val subject = newSubject(savedStateHandle)
            subject.onLeagueClicked(null)

            // Then
            assertThat(savedStateHandle.savedLeagueId, `is`(nullValue()))
        }
    }

    @Test
    fun `when app is restored, it should restore the previous league as selected`() {
        runTest {
            // Given
            val savedStateHandle = SavedStateHandle()
            val league = League(id = "1")
            val teams = listOf(
                Team(),
                Team(),
                Team(),
            )
            every { scheduleAndObserveWorkersUseCase.invoke() } returns flowOf(false)
            coEvery { getLeagueByIdUseCase(league.id) } returns league
            coEvery { listTeamsByLeagueIdUseCase(league.id) } returns teams
            savedStateHandle.savedLeagueId = league.id

            // When
            val subject = newSubject(savedStateHandle)

            // Then
            subject.uiState.test {
                val uiState = awaitItem()
                assertThat(uiState.selectedLeague, `is`(league))
                assertThat(uiState.teams, `is`(teams))
            }
        }
    }
}
