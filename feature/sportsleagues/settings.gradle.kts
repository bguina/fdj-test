include(":feature:sportsleagues")
include(":feature:sportsleagues:data")
include(":feature:sportsleagues:data:core")
include(":feature:sportsleagues:domain")
include(":feature:sportsleagues:presentation")

apply {
    from("data/settings.gradle.kts")
}
