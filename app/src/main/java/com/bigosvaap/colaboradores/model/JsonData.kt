package com.bigosvaap.colaboradores.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonData(
    @field:Json(name = "data")
    val data: Empleados
)

@JsonClass(generateAdapter = true)
data class Empleados(
    @field:Json(name = "employees")
    val employess: List<Empleado>
)