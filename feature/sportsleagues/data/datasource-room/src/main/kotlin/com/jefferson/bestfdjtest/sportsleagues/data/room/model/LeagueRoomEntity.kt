package com.jefferson.bestfdjtest.sportsleagues.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = LeagueRoomEntity.TABLE_NAME,
)
data class LeagueRoomEntity(
    @PrimaryKey @ColumnInfo(name = FIELD_ID) val id: String,
    @ColumnInfo(name = FIELD_SPORT) val sport: String,
    @ColumnInfo(name = FIELD_NAME) val name: String,
    @ColumnInfo(name = FIELD_ALTNAME) val altName: String?,
) {

    companion object {
        const val TABLE_NAME: String = "leagues"

        const val FIELD_ID: String = "id"
        const val FIELD_SPORT: String = "sport"
        const val FIELD_NAME: String = "name"
        const val FIELD_ALTNAME: String = "altname"
    }
}
