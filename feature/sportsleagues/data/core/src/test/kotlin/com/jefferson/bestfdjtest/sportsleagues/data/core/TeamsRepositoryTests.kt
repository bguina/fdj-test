package com.jefferson.bestfdjtest.sportsleagues.data.core

import com.jefferson.bestfdjtest.sportsleague.data.core.TeamsRepository
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ITeamsNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.model.TeamEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class TeamsRepositoryTests {

    @MockK
    private lateinit var networkDataSource: ITeamsNetworkDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `teams which do not belong to the league should be discarded`() {
        runTest {
            // Given
            val leagueName = "AMA Supercross"
            val exampleTeamToKeep = mockk<TeamEntity> {
                every { id } returns "0"
                every { name } returns "Some $leagueName team"
                every { leaguesNames } returns listOf(leagueName)
                every { badgeImageUrl } returns ""
            }
            val exampleTeamToDiscard = mockk<TeamEntity> {
                every { id } returns "0"
                every { name } returns "Discarded team"
                every { leaguesNames } returns listOf("Some other SuperCup")
                every { badgeImageUrl } returns ""
            }
            val resultTeams = listOf(
                exampleTeamToDiscard,
                exampleTeamToDiscard,
                exampleTeamToKeep,
                exampleTeamToDiscard,
            )
            coEvery { networkDataSource.searchTeams(leagueName) } returns resultTeams

            // When
            val subject = TeamsRepository(
                networkDataSource = networkDataSource,
            )
            val teams = subject.searchTeamsFromLeague(leagueName)

            // Then
            assertThat(teams.size, `is`(1))
        }
    }
}
