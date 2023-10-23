package com.jefferson.bestfdjtest.sportsleagues.domain.interactor

import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GetLeagueByIdUseCaseTests {

    @MockK
    private lateinit var leaguesRepository: ILeaguesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `it should return the league`() {
        runTest {
            val dummyLeague = League(
                id = "1",
            )
            coEvery {
                leaguesRepository.getLeagueById("1")
            } returns dummyLeague

            // When
            val subject = GetLeagueByIdUseCase(
                leaguesRepository = leaguesRepository,
            )
            val league = subject(dummyLeague.id)

            // Then
            assertThat(league, `is`(dummyLeague))
        }
    }
}
