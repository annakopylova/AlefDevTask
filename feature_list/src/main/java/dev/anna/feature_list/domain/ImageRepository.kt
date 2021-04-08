package dev.anna.feature_list.domain

import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getList(): Flow<Result<List<String>>>
}