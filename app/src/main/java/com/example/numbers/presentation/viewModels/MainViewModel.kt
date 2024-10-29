package com.example.numbers.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.numbers.data.repository.FactsRepository
import com.example.numbers.presentation.mappers.mapToFactUi
import com.example.numbers.presentation.ui.models.FactUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.map

class MainViewModel(
    private val repository: FactsRepository
) : ViewModel() {

    val uiState: StateFlow<List<FactUi>>
        get() = getAllFacts()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = listOf<FactUi>()
            )

    fun getAllFacts(): Flow<List<FactUi>> {
        return repository.getAllFacts()
            .map { facts ->
                facts.map { it.mapToFactUi() }
            }
    }

    fun getFactByNumber(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFactAboutNumber(number)
        }
    }

    fun getFactAboutRandomNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFactAboutRandomNumber()
        }
    }

    companion object {

        fun createFactory(repository: FactsRepository) = viewModelFactory {
            initializer {
                MainViewModel(repository)
            }
        }
    }
}