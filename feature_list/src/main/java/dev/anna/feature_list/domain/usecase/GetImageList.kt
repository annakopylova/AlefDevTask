package dev.anna.feature_list.domain.usecase

import dev.anna.feature_list.domain.ImageRepository

class GetImageList(private val imageRepository: ImageRepository) {
    suspend operator fun invoke() = imageRepository.getList()
}