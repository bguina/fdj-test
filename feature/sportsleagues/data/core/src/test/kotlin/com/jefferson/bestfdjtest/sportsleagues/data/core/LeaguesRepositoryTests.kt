package com.jefferson.bestfdjtest.sportsleagues.data.core

import com.jefferson.bestfdjtest.sportsleague.data.core.LeaguesRepository
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesLocalDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.worker.ILeaguesSyncWorkScheduler
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class LeaguesRepositoryTests {

    @MockK
    private lateinit var syncWorkScheduler: ILeaguesSyncWorkScheduler

    @MockK
    private lateinit var leaguesLocalDataSource: ILeaguesLocalDataSource

    @MockK
    private lateinit var leaguesNetworkDataSource: ILeaguesNetworkDataSource

    private fun newSubject(): LeaguesRepository = LeaguesRepository(
        syncScheduler = syncWorkScheduler,
        localDataSource = leaguesLocalDataSource,
        networkDataSource = leaguesNetworkDataSource,
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when leagues table is empty, a sync should be required`() {
        runTest {
            // Given
            coEvery { leaguesLocalDataSource.isEmpty() } returns true

            // When
            val subject = newSubject()
            val isSyncRequired = subject.requiresSync()

            // Then
            assertThat(isSyncRequired, `is`(true))
        }
    }

    @Test
    fun `when leagues table is not empty, a sync should not be required`() {
        runTest {
            // Given
            coEvery { leaguesLocalDataSource.isEmpty() } returns false

            // When
            val subject = newSubject()
            val isSyncRequired = subject.requiresSync()

            // Then
            assertThat(isSyncRequired, `is`(false))
        }
    }
}
