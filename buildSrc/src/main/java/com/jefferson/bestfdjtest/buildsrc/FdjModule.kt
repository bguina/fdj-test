package com.jefferson.bestfdjtest.buildsrc

open class FdjModule(name: String, path: String = "") {
    val api: String = "$path:$name"
    val domain: String = "$api:domain"
    val data: String = "$api:data"
    val core: String = "$data:core"
    val presentation: String = "$path:$name:presentation"

    object Style : FdjModule(
        name = "style",
    )

    object Errors : FdjModule(
        name = "errors",
        path = ":feature",
    )

    object SportsLeagues : FdjModule(
        name = "sportsleagues",
        path = ":feature",
    ) {

        object Room : FdjModule(
            name = "datasource-room",
            path = ":${SportsLeagues.data}",
        )

        object TheSportsDb : FdjModule(
            name = "datasource-thesportsdb",
            path = ":${SportsLeagues.data}",
        )
    }

    object TheSportsDbClient : FdjModule(name = "thesportsdb-client")
}
