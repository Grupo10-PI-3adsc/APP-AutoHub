package com.example.appautohub.data.state

sealed class StatusUiState {
    object Idle : StatusUiState()
    object Loading : StatusUiState()
    data class Success(val status: String) : StatusUiState()
    data class Error(val message: String) : StatusUiState()
}
