package dev.anna.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitServiceImpl : RetrofitService {

    lateinit var api: API

    init {
        api = buildRetrofit()
    }

    private fun buildRetrofit(): API {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("http://dev-tasks.alef.im/")
            .build()
            .create(API::class.java)
    }

    override suspend fun getImages(): Result<List<String>> {
        return try {
            Result.success(api.imageResponse())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}