package com.jefferson.bestfdjtest.sportsleagues.domain.interactor

import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ITeamsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.hasItems
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ListTeamsByLeagueIdTests {

    @MockK
    private lateinit var leaguesRepository: ILeaguesRepository

    @MockK
    private lateinit var teamsRepository: ITeamsRepository

    private fun newSubject(): ListTeamsByLeagueIdUseCase = ListTeamsByLeagueIdUseCase(
        leaguesRepository = leaguesRepository,
        teamsRepository = teamsRepository,
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `teams should be filtered so every 1 out of 2 are discarded`() {
        runTest {
            // Given
            val dummyLeague = mockk<League> {
                mockk {
                    every { id } returns "0"
                    every { name } returns ""
                }
            }
            coEvery { leaguesRepository.getLeagueById("0") } returns dummyLeague

            val dummy1 = Team(
                name = "1",
                leaguesNames = listOf(""),
            )
            val dummy2 = Team(
                name = "2",
                leaguesNames = listOf(""),
            )
            val dummy3 = Team(
                name = "3",
                leaguesNames = listOf(""),
            )
            val resultTeams = listOf(
                dummy1,
                dummy2,
                dummy3,
            )
            coEvery { teamsRepository.searchTeamsFromLeague(any()) } returns resultTeams

            // When
            val subject = newSubject()
            val teams = subject(dummyLeague.id)

            // Then
            if (dummy1 in teams) {
                assertThat(teams.size, `is`(2))
                assertThat(teams, hasItems(dummy1, dummy3))
            } else {
                assertThat(teams.size, `is`(1))
                assertThat(teams, hasItem(dummy2))
            }
        }
    }

    @Test
    fun `teams should be sorted alphabetically descending`() {
        runTest {
            // Given
            val dummyLeague = League(
                id = "0",
                name = "AMA Supercross",
            )
            val resultTeams = listOf(
                Team(
                    leaguesNames = listOf(dummyLeague.name),
                    name = "A",
                ),
                Team(
                    leaguesNames = listOf(dummyLeague.name),
                    name = "C",
                ),
                Team(
                    leaguesNames = listOf(dummyLeague.name),
                    name = "B",
                ),
            )
            coEvery { leaguesRepository.getLeagueById(dummyLeague.id) } returns dummyLeague
            coEvery { teamsRepository.searchTeamsFromLeague(dummyLeague.name) } returns resultTeams

            // When
            val subject = newSubject()
            val teams = subject(dummyLeague.id)

            // Then
            assertThat(teams, `is`(teams.sortedByDescending { it.name }))
        }
    }
}
