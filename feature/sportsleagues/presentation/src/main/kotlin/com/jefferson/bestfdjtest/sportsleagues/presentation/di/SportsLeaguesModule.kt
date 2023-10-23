package com.jefferson.bestfdjtest.sportsleagues.presentation.di

import android.app.Application
import androidx.room.Room
import com.jefferson.bestfdjtest.sportsleague.data.core.LeaguesRepository
import com.jefferson.bestfdjtest.sportsleague.data.core.TeamsRepository
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesLocalDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ITeamsNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.worker.ILeaguesSyncWorkScheduler
import com.jefferson.bestfdjtest.sportsleague.data.core.worker.SportsLeaguesSyncWorkScheduler
import com.jefferson.bestfdjtest.sportsleague.data.thesportsdb.TheSportsDbLeaguesDataSource
import com.jefferson.bestfdjtest.sportsleague.data.thesportsdb.TheSportsDbTeamsDataSource
import com.jefferson.bestfdjtest.sportsleagues.data.room.LeaguesRoomDao
import com.jefferson.bestfdjtest.sportsleagues.data.room.RoomLeaguesDataSource
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ITeamsRepository
import com.jefferson.bestfdjtest.sportsleagues.presentation.SportsLeaguesDatabase
import com.jefferson.thesportsdb.client.TheSportsDbApiClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient

@Module(
    includes = [
        SportsLeaguesModule.LeaguesModule::class,
        SportsLeaguesModule.TeamsModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class SportsLeaguesModule {

    @Module(
        includes = [
            LeaguesModule.BindsModule::class,
        ]
    )
    @InstallIn(SingletonComponent::class)
    class LeaguesModule {
        @Provides
        fun provideSportsLeaguesDatabase(
            application: Application,
        ): SportsLeaguesDatabase = Room.databaseBuilder(
            context = application,
            klass = SportsLeaguesDatabase::class.java,
            name = DATABASE_NAME,
        )
            .build()

        @Provides
        fun provideLeaguesDao(
            sportsLeaguesDatabase: SportsLeaguesDatabase
        ): LeaguesRoomDao = sportsLeaguesDatabase.leaguesDao()

        @Module
        @InstallIn(SingletonComponent::class)
        interface BindsModule {
            @Binds
            fun bindLeaguesRepository(
                leaguesRepository: LeaguesRepository
            ): ILeaguesRepository

            @Binds
            fun bindLeaguesSyncScheduler(
                leaguesSyncScheduler: SportsLeaguesSyncWorkScheduler
            ): ILeaguesSyncWorkScheduler

            @Binds
            fun bindLeaguesLocalDataSource(
                leaguesLocalDataSource: RoomLeaguesDataSource
            ): ILeaguesLocalDataSource

            @Binds
            fun bindLeaguesNetworkDataSource(
                leaguesNetworkDataSource: TheSportsDbLeaguesDataSource
            ): ILeaguesNetworkDataSource
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface TeamsModule {

        @Binds
        fun provideTeamsRepository(
            teamsRepository: TeamsRepository
        ): ITeamsRepository

        @Binds
        fun provideTeamsNetworkDataSource(
            teamsNetworkDataSource: TheSportsDbTeamsDataSource
        ): ITeamsNetworkDataSource
    }

    @Provides
    fun provideTheSportsDbApiClient(
        baseClient: OkHttpClient,
    ): TheSportsDbApiClient = TheSportsDbApiClient(
        baseClient = baseClient,
        dispatcher = Dispatchers.IO,
        apiKey = THESPORTSDB_API_KEY,
    )

    companion object {
        private const val DATABASE_NAME = "fdj-db"

        private const val THESPORTSDB_API_KEY = "50130162"
    }
}
