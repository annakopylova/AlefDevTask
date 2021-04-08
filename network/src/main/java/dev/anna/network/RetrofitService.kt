package dev.anna.network

interface RetrofitService {
    suspend fun getImages(): Result<List<String>>
}