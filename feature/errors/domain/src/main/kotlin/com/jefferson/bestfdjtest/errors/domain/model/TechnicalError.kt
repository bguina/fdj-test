package com.jefferson.bestfdjtest.errors.domain.model

sealed class TechnicalError(
    message: String? = null,
    cause: Throwable?,
    val supportTeam: SupportTeam,
) : Throwable(message = message, cause = cause) {

    class Data(
        message: String? = null,
        supportTeam: SupportTeam = SupportTeam.MyFavouriteFreelancer,
        cause: Throwable? = null,
    ) : TechnicalError(
        message = message,
        cause = cause,
        supportTeam = supportTeam
    )

    class Database(
        message: String? = null,
        supportTeam: SupportTeam = SupportTeam.MyFavouriteFreelancer,
        cause: Throwable? = null,
    ) : TechnicalError(
        message = message,
        cause = cause,
        supportTeam = supportTeam
    )

    class Network(
        message: String? = null,
        supportTeam: SupportTeam = SupportTeam.MyFavouriteFreelancer,
        cause: Throwable? = null,
    ) : TechnicalError(
        message = message,
        cause = cause,
        supportTeam = supportTeam
    )
}
