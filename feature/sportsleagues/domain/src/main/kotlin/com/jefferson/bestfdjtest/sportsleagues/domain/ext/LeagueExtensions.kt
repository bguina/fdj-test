package com.jefferson.bestfdjtest.sportsleagues.domain.ext

import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import java.lang.Integer.min

fun League.nameSimilarityScore(
    needle: String?,
): Int = needle?.let {
    min(
        it.levenshteinDistanceWith(name),
        it.levenshteinDistanceWith(altName),
    )
} ?: Int.MAX_VALUE
