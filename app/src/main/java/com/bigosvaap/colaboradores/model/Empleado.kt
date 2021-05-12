package com.bigosvaap.colaboradores.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "empleados")
data class Empleado(

    @field:Json(name = "id")
    @PrimaryKey
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "location")
    @Embedded
    val location: Location,
    @field:Json(name = "mail")
    val email: String

){

    fun toHashMapOf() = hashMapOf(
        "id" to id,
        "name" to name,
        "latitude" to location.latitude,
        "longitude" to location.longitude,
        "email" to email
    )

}

@JsonClass(generateAdapter = true)
data class Location(

    @field:Json(name = "lat")
    val latitude: Double,
    @field:Json(name = "log")
    val longitude: Double

)
