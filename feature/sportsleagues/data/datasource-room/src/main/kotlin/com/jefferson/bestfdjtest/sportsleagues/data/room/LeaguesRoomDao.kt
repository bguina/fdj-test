package com.jefferson.bestfdjtest.sportsleagues.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jefferson.bestfdjtest.sportsleagues.data.room.model.LeagueRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaguesRoomDao {

    @Query("DELETE FROM ${LeagueRoomEntity.TABLE_NAME}")
    suspend fun clear(
    )

    @Insert
    suspend fun insertAll(
        friends: List<LeagueRoomEntity>,
    )

    @Query(
        value = "SELECT COUNT(${LeagueRoomEntity.FIELD_ID}) FROM ${LeagueRoomEntity.TABLE_NAME}",
    )
    suspend fun count(
    ): Long

    @Query(
        value = """
        SELECT * FROM ${LeagueRoomEntity.TABLE_NAME}
        WHERE ${LeagueRoomEntity.FIELD_ID} = :leagueId
    """,
    )
    fun getLeagueById(
        leagueId: String,
    ): LeagueRoomEntity?

    @Query(
        value = " SELECT * FROM ${LeagueRoomEntity.TABLE_NAME}",
    )
    fun listAll(
    ): Flow<List<LeagueRoomEntity>>

    @Query(
        value = """
        SELECT * FROM ${LeagueRoomEntity.TABLE_NAME}
        WHERE ${LeagueRoomEntity.FIELD_NAME} LIKE '%' || :query || '%' OR
        ${LeagueRoomEntity.FIELD_ALTNAME} LIKE '%' || :query || '%'
    """,
    )
    fun searchInNames(
        query: String,
    ): Flow<List<LeagueRoomEntity>>
}
