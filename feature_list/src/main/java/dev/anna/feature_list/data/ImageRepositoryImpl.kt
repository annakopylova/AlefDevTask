package dev.anna.feature_list.data

import dev.anna.feature_list.domain.ImageDataSource
import dev.anna.feature_list.domain.ImageRepository
import kotlinx.coroutines.flow.Flow

class ImageRepositoryImpl(private val dataSource: ImageDataSource): ImageRepository {
    override suspend fun getList(): Flow<Result<List<String>>> = dataSource.getList()
}