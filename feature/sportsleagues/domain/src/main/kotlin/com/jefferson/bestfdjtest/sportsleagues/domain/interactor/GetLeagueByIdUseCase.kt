package com.jefferson.bestfdjtest.sportsleagues.domain.interactor

import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import javax.inject.Inject

class GetLeagueByIdUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepository,
) {
    suspend operator fun invoke(
        leagueId: String,
    ): League = leaguesRepository.getLeagueById(
        leagueId = leagueId,
    )
}
