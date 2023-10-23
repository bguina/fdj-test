package com.jefferson.thesportsdb.client

import com.jefferson.thesportsdb.client.model.TheSportsDBV1LeagueDto
import com.jefferson.thesportsdb.client.model.TheSportsDBV1TeamDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TheSportsDbApiClient(
    baseClient: OkHttpClient,
    private val dispatcher: CoroutineDispatcher,
    private val apiKey: String,
    baseUrl: String = TheSportsDbService.PRODUCTION_URL
) {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val service: TheSportsDbService = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .client(baseClient)
        .build()
        .create(TheSportsDbService::class.java)

    suspend fun listAllLeagues(
    ): List<TheSportsDBV1LeagueDto> = withContext(dispatcher) {
        service.listAllLeagues(
            apiKey = apiKey
        ).leagues.orEmpty()
    }

    suspend fun getLeague(
        leagueId: String,
    ): TheSportsDBV1LeagueDto = withContext(dispatcher) {
        service.lookupLeague(
            apiKey = apiKey,
            leagueId = leagueId,
        )
            .leagues?.firstOrNull()
            ?: throw NoSuchElementException("no such league ID: $leagueId")
    }

    suspend fun searchTeams(
        query: String,
    ): List<TheSportsDBV1TeamDto> = withContext(dispatcher) {
        service.searchAllTeams(
            apiKey = apiKey,
            query = query,
        ).teams.orEmpty()
    }
}
