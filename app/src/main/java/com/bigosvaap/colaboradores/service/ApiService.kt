package com.bigosvaap.colaboradores.service


import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("https://dl.dropboxusercontent.com/s/5u21281sca8gj94/getFile.json?dl=0")
    suspend fun getData(): ServiceResponse

    @GET
    suspend fun downloadFile(@Url fileUrl: String): ResponseBody

}