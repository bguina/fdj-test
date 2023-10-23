package com.jefferson.bestfdjtest.errors.domain.model

sealed class SupportTeam(
    val name: String,
) {
    object MyFavouriteFreelancer : SupportTeam("team-freelancer")
}
