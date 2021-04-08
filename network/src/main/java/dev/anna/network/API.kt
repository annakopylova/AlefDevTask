package dev.anna.network

import retrofit2.http.GET

interface API {
    //список картинок
    @GET("task-m-001/list.php")
    suspend fun imageResponse(): List<String>
}