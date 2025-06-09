package com.example.appautohub.data.state

import com.example.appautohub.data.remote.PixResponse

sealed class PixUiState {
    object Idle : PixUiState()
    object Loading : PixUiState()
    data class Success(val response: PixResponse) : PixUiState()
    data class Error(val message: String) : PixUiState()
}
