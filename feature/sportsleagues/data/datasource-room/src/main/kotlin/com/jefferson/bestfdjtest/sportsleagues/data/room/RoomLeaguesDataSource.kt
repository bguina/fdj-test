package com.jefferson.bestfdjtest.sportsleagues.data.room

import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesLocalDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.model.LeagueEntity
import com.jefferson.bestfdjtest.sportsleagues.data.room.model.LeagueRoomEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomLeaguesDataSource @Inject constructor(
    private val leaguesDao: LeaguesRoomDao,
) : ILeaguesLocalDataSource {

    override suspend fun emplace(
        leagues: List<LeagueEntity>,
    ) {
        leaguesDao.clear()
        leaguesDao.insertAll(leagues.map { it.toDbEntity() })
    }

    override suspend fun isEmpty(
    ): Boolean = 0L == leaguesDao.count()

    override suspend fun getLeagueById(
        leagueId: String,
    ): LeagueEntity = withContext(Dispatchers.IO) {
        leaguesDao.getLeagueById(
            leagueId = leagueId,
        )
            ?.toEntity()
            ?: throw NoSuchFieldException("league $leagueId")
    }

    override fun listAll(
    ): Flow<List<LeagueEntity>> = leaguesDao.listAll(
    )
        .map {
            it
                .map { league -> league.toEntity() }
                .excludingTechnicalLeagues()
        }

    override fun searchInNames(
        query: String,
    ): Flow<List<LeagueEntity>> = leaguesDao.searchInNames(
        query = query,
    ).map {
        it
            .map { league -> league.toEntity() }
            .excludingTechnicalLeagues()
    }

    /**
     *  Remove presence of "technical" leagues which are marked with a prefix
     * */
    private fun List<LeagueEntity>.excludingTechnicalLeagues(
    ): List<LeagueEntity> = filterNot { league ->
        true == league.name?.startsWith(TECHNICAL_LEAGUE_PREFIX)
    }

    private fun LeagueEntity.toDbEntity(): LeagueRoomEntity = LeagueRoomEntity(
        id = id ?: throw NoSuchFieldException("id"),
        sport = sport ?: throw NoSuchFieldException("sport"),
        name = name ?: throw NoSuchFieldException("name"),
        altName = altName,
    )

    private fun LeagueRoomEntity.toEntity(): LeagueEntity = LeagueEntity(
        id = id,
        sport = sport,
        name = name,
        altName = altName,
    )

    companion object {
        private const val TAG: String = "LeaguesRoomDataSource"
        private const val TECHNICAL_LEAGUE_PREFIX: String = "_"
    }
}
