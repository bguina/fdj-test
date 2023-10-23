package com.jefferson.thesportsdb.client

import com.jefferson.thesportsdb.client.model.TheSportsDBV1LeaguesDto
import com.jefferson.thesportsdb.client.model.TheSportsDBV1TeamsDto
import retrofit2.http.*

interface TheSportsDbService {
    @GET("v1/json/{apiKey}/all_leagues.php")
    suspend fun listAllLeagues(
        @Path("apiKey") apiKey: String,
    ): TheSportsDBV1LeaguesDto

    @GET("v1/json/{apiKey}/lookupleague.php")
    suspend fun lookupLeague(
        @Path("apiKey") apiKey: String,
        @Query("id") leagueId: String,
    ): TheSportsDBV1LeaguesDto

    @GET("v1/json/{apiKey}/search_all_teams.php")
    suspend fun searchAllTeams(
        @Path("apiKey") apiKey: String,
        @Query("l") query: String? = null,
    ): TheSportsDBV1TeamsDto

    companion object {
        const val PRODUCTION_URL: String = "https://www.thesportsdb.com/api/"
    }
}
