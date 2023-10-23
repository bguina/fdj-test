package com.jefferson.bestfdjtest.sportsleagues.domain.interactor

import com.jefferson.bestfdjtest.errors.domain.interactor.ReportExceptionUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.ext.nameSimilarityScore
import com.jefferson.bestfdjtest.sportsleagues.domain.ext.sanitized
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveLeagueSuggestionsUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepository,
    private val reportExceptionUseCase: ReportExceptionUseCase,
) {

    operator fun invoke(
        query: String,
    ): Flow<List<League>> = leaguesRepository.observePeriodicSyncs().flatMapLatest { isSyncing ->
        if (!isSyncing) {
            val sanitizedQuery = query.sanitized()

            sanitizedQuery.suggestionsFlow.map { suggestions ->
                suggestions
                    .sortedWith(
                        compareBy<League> {
                            it.nameSimilarityScore(sanitizedQuery)
                        }
                            .thenBy { it.name }
                    )
            }
        } else flowOf(emptyList())
    }
        .catch { e ->
            reportExceptionUseCase(e)
            throw e
        }

    private val String?.suggestionsFlow: Flow<List<League>>
        get() = when {
            !isNullOrEmpty() ->
                leaguesRepository.searchInNames(
                    query = this,
                )

            else ->
                leaguesRepository.listAll()
        }
}
