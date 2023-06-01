package com.example.countriessimple.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Country(
    @Json(name = "name")
    @ColumnInfo(name = "name")
    val countryName: String?,
    @Json(name = "flag")
    @ColumnInfo(name = "flag")
    val imageUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
