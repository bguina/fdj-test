package com.jefferson.thesportsdb.client.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class TheSportsDBV1LeagueDto(
    @Json(name = "strDescriptionES")
    val strDescriptionES: String? = null,
    @Json(name = "dateFirstEvent")
    val dateFirstEvent: String? = null,
    @Json(name = "intDivision")
    val intDivision: String? = null,
    @Json(name = "intFormedYear")
    val intFormedYear: String? = null,
    @Json(name = "strBanner")
    val strBanner: String? = null,
    @Json(name = "strSport")
    val strSport: String? = null,
    @Json(name = "strInstagram")
    val strInstagram: String? = null,
    @Json(name = "strDescriptionIT")
    val strDescriptionIT: String? = null,
    @Json(name = "strDescriptionCN")
    val strDescriptionCN: String? = null,
    @Json(name = "strDescriptionEN")
    val strDescriptionEN: String? = null,
    @Json(name = "strWebsite")
    val strWebsite: String? = null,
    @Json(name = "strYoutube")
    val strYoutube: String? = null,
    @Json(name = "strDescriptionIL")
    val strDescriptionIL: String? = null,
    @Json(name = "idCup")
    val idCup: String? = null,
    @Json(name = "strComplete")
    val strComplete: String? = null,
    @Json(name = "idAPIfootball")
    val idAPIfootball: String? = null,
    @Json(name = "strLocked")
    val strLocked: String? = null,
    @Json(name = "idLeague")
    val idLeague: String? = null,
    @Json(name = "idSoccerXML")
    val idSoccerXML: String? = null,
    @Json(name = "strTrophy")
    val strTrophy: String? = null,
    @Json(name = "strBadge")
    val strBadge: String? = null,
    @Json(name = "strTwitter")
    val strTwitter: String? = null,
    @Json(name = "strDescriptionHU")
    val strDescriptionHU: String? = null,
    @Json(name = "strGender")
    val strGender: String? = null,
    @Json(name = "strLeagueAlternate")
    val strLeagueAlternate: String? = null,
    @Json(name = "strDescriptionSE")
    val strDescriptionSE: String? = null,
    @Json(name = "strNaming")
    val strNaming: String? = null,
    @Json(name = "strDescriptionJP")
    val strDescriptionJP: String? = null,
    @Json(name = "strFanart1")
    val strFanart1: String? = null,
    @Json(name = "strDescriptionFR")
    val strDescriptionFR: String? = null,
    @Json(name = "strFanart2")
    val strFanart2: String? = null,
    @Json(name = "strTvRights")
    val strTvRights: String? = null,
    @Json(name = "strFanart3")
    val strFanart3: String? = null,
    @Json(name = "strFacebook")
    val strFacebook: String? = null,
    @Json(name = "strFanart4")
    val strFanart4: String? = null,
    @Json(name = "strCountry")
    val strCountry: String? = null,
    @Json(name = "strDescriptionNL")
    val strDescriptionNL: String? = null,
    @Json(name = "strRSS")
    val strRSS: String? = null,
    @Json(name = "strDescriptionRU")
    val strDescriptionRU: String? = null,
    @Json(name = "strDescriptionPT")
    val strDescriptionPT: String? = null,
    @Json(name = "strLogo")
    val strLogo: String? = null,
    @Json(name = "strDescriptionDE")
    val strDescriptionDE: String? = null,
    @Json(name = "strDescriptionNO")
    val strDescriptionNO: String? = null,
    @Json(name = "strLeague")
    val strLeague: String? = null,
    @Json(name = "strCurrentSeason")
    val strCurrentSeason: String? = null,
    @Json(name = "strPoster")
    val strPoster: String? = null,
    @Json(name = "strDescriptionPL")
    val strDescriptionPL: String? = null,
)