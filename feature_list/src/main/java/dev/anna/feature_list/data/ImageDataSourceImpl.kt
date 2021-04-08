package dev.anna.feature_list.data

import dev.anna.feature_list.domain.ImageDataSource
import dev.anna.network.RetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageDataSourceImpl(private val retrofitService: RetrofitService) : ImageDataSource {
    override suspend fun getList(): Flow<Result<List<String>>> = flow {
        val response = retrofitService.getImages()
        if (response.isSuccess) {
            emit(Result.success(response.getOrNull()!!))
        } else {
            emit(Result.failure<List<String>>(response.exceptionOrNull()!!))
        }
    }
}