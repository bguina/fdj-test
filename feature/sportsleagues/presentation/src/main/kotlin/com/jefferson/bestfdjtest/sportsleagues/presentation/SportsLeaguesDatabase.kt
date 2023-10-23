package com.jefferson.bestfdjtest.sportsleagues.presentation

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jefferson.bestfdjtest.sportsleagues.data.room.LeaguesRoomDao
import com.jefferson.bestfdjtest.sportsleagues.data.room.model.LeagueRoomEntity

@Database(
    entities = [
        LeagueRoomEntity::class,
        // a team table would run too heavy on the API
    ],
    version = SportsLeaguesDatabase.DATABASE_VERSION,
)
abstract class SportsLeaguesDatabase : RoomDatabase() {

    abstract fun leaguesDao(): LeaguesRoomDao

    companion object {
        const val DATABASE_VERSION: Int = 1
    }
}
