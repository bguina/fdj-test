package com.jefferson.bestfdjtest.sportsleagues.presentation.ext

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Diversity1
import androidx.compose.material.icons.filled.DownhillSkiing
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MergeType
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Skateboarding
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsCricket
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.SportsFootball
import androidx.compose.material.icons.filled.SportsGolf
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material.icons.filled.SportsHockey
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.material.icons.filled.SportsRugby
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material.icons.filled.Surfing
import androidx.compose.material.icons.filled.Water
import androidx.compose.ui.graphics.vector.ImageVector
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League

val League.sportIcon: ImageVector
    get() = sport.run {
        when {
            contains("athletics", true) -> Icons.Default.Equalizer
            contains("badminton", true) -> Icons.Default.SportsTennis
            contains("baseball", true) -> Icons.Default.SportsBaseball
            contains("basketball", true) -> Icons.Default.SportsBasketball
            contains("cycling", true) -> Icons.Default.PedalBike
            contains("cricket", true) -> Icons.Default.SportsCricket
            contains("esport", true) -> Icons.Default.SportsEsports
            contains("extreme", true) -> Icons.Default.Surfing
            contains("fighting", true) -> Icons.Default.SportsMartialArts
            contains("football", true) -> Icons.Default.SportsFootball
            contains("golf", true) -> Icons.Default.SportsGolf
            contains("gymnastics", true) -> Icons.Default.SportsGymnastics
            contains("handball", true) -> Icons.Default.SportsHandball
            contains("hockey", true) -> Icons.Default.SportsHockey
            contains("motorsport", true) -> Icons.Default.SportsMotorsports
            contains("rugby", true) -> Icons.Default.SportsRugby
            contains("skating", true) -> Icons.Default.Skateboarding
            contains("skiing", true) -> Icons.Default.DownhillSkiing
            contains("soccer", true) -> Icons.Default.SportsSoccer
            contains("shooting", true) -> Icons.Default.LocalFireDepartment
            contains("tennis", true) -> Icons.Default.SportsTennis
            contains("volleyball", true) -> Icons.Default.SportsVolleyball
            contains("watersports", true) -> Icons.Default.Water
            contains("weightlifting", true) -> Icons.Default.MergeType
            contains("wintersports", true) -> Icons.Default.DownhillSkiing

            contains("multi", true) -> Icons.Default.DirectionsRun
            else -> Icons.Default.Diversity1
        }
    }
