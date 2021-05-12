package com.bigosvaap.colaboradores.service

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServiceResponse(
    @field:Json(name = "data")
    val data: File,
    @field:Json(name = "code")
    val code: Int,
    @field:Json(name = "success")
    val success: Boolean
)

@JsonClass(generateAdapter = true)
data class File(
    @field:Json(name = "file")
    val path: String
)