package com.example.numbers.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.numbers.data.repository.FactsRepository
import com.example.numbers.presentation.mappers.mapToFactUi
import com.example.numbers.presentation.ui.models.FactUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.map

data class MainUiState(
    val errorMessage: String? = null,
    val currentFact: FactUi? = null,
    val searchHistory: List<FactUi> = listOf()
)

class MainViewModel(
    private val repository: FactsRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState>
        get() = _uiState
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MainUiState()
            )

    init {
        getAllFacts()
    }

    fun getAllFacts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFacts()
                .catch { e -> _uiState.update { it.copy(errorMessage = e.message) } }
                .map { facts ->
                    facts.map { it.mapToFactUi() }
                }
                .collect { facts ->
                    _uiState.update {
                        it.copy(searchHistory = facts)
                    }
                }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update { it.copy(errorMessage = throwable.message) }
    }

    fun getFactByNumber(number: Long) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repository.getFactAboutNumber(number)
        }
    }

    fun getFactAboutRandomNumber() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repository.getFactAboutRandomNumber()
        }
    }

    fun setCurrentFactById(id: Long) {
        val currentFact = _uiState.value.searchHistory.find { it.id == id }
        _uiState.update { it.copy(currentFact = currentFact) }
    }

    fun setErrorMessage(message: String) {
        _uiState.update { it.copy(errorMessage = message) }
    }

    fun clearErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    companion object {

        fun createFactory(repository: FactsRepository) = viewModelFactory {
            initializer {
                MainViewModel(repository)
            }
        }
    }
}