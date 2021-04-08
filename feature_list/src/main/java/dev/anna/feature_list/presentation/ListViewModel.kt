package dev.anna.feature_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.anna.feature_list.domain.usecase.GetImageList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(private val getImageList: GetImageList) : ViewModel() {

    val state = MutableStateFlow(State())
    val error = MutableStateFlow("")

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            getImageList().collect { result ->
                if (result.isSuccess) {
                    state.emit(State(result.getOrNull()!!, System.currentTimeMillis()))
                } else {
                    result.exceptionOrNull()?.message?.let {
                        error.emit(it)
                    }
                }
            }
        }
    }

    data class State(val list: List<String> = listOf(), val time: Long = 0L)
}