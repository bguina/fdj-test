package com.jefferson.bestfdjtest.sportsleagues.domain.interactor

import app.cash.turbine.test
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ScheduleAndObserveWorkersUseCaseTests {

    @MockK
    private lateinit var leaguesRepository: ILeaguesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `starting to observe should schedule a sync`() {
        runTest {
            val dummySyncFlow = flowOf(false)
            coEvery { leaguesRepository.observePeriodicSyncs() } returns dummySyncFlow

            // When
            val subject = ScheduleAndObserveWorkersUseCase(
                leaguesRepository = leaguesRepository,
            )
            subject().test {
                assertThat(awaitItem(), `is`(false))
                awaitComplete()
            }

            // Then
            coVerify { leaguesRepository.scheduleSyncs(any()) }
        }
    }
}
