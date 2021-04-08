package dev.anna.feature_list.domain

import kotlinx.coroutines.flow.Flow

interface ImageDataSource {
    suspend fun getList(): Flow<Result<List<String>>>
}