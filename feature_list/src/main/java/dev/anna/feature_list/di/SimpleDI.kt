package dev.anna.feature_list.di

import dev.anna.feature_list.data.ImageDataSourceImpl
import dev.anna.feature_list.data.ImageRepositoryImpl
import dev.anna.feature_list.domain.usecase.GetImageList
import dev.anna.feature_list.presentation.ListViewModel
import dev.anna.network.RetrofitServiceImpl

object SimpleDI {
    fun getViewModel(): ListViewModel {
        val dataSource = ImageDataSourceImpl(RetrofitServiceImpl())
        val repository = ImageRepositoryImpl(dataSource)
        val usecase = GetImageList(repository)
        val viewModel = ListViewModel(usecase)
        return viewModel
    }
}