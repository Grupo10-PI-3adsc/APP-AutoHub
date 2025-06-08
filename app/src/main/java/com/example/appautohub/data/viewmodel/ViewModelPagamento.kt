package com.example.appautohub.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appautohub.data.model.LoginResponse
import com.example.appautohub.data.remote.*
import com.example.appautohub.data.remote.RetrofitPagamento.apiPagamento
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Estado para consultarStatus
sealed class StatusUiState {
    object Idle : StatusUiState()
    object Loading : StatusUiState()
    data class Success(val status: StatusResponse) : StatusUiState()
    data class Error(val message: String) : StatusUiState()
}

// Novo estado para gerarPix
sealed class PixUiState {
    object Idle : PixUiState()
    object Loading : PixUiState()
    data class Success(val pixResponse: PixResponse) : PixUiState()
    data class Error(val message: String) : PixUiState()
}

class ViewModelPagamento : ViewModel() {

    var loginResponse by mutableStateOf<LoginResponse?>(null)
        private set

    val id = loginResponse?.id.toString()

    // Estado para consultar status do pagamento
    private val _uiState = MutableStateFlow<StatusUiState>(StatusUiState.Idle)
    val uiState: StateFlow<StatusUiState> = _uiState

    // Estado para gerar Pix
    private val _pixUiState = MutableStateFlow<PixUiState>(PixUiState.Idle)
    val pixUiState: StateFlow<PixUiState> = _pixUiState

    fun consultarStatus() {
        _uiState.value = StatusUiState.Loading

        viewModelScope.launch {
            try {
                val response = apiPagamento.consultarStatus(UsuarioIdRequest(id)).execute()

                if (response.isSuccessful && response.body() != null) {
                    _uiState.value = StatusUiState.Success(response.body()!!)
                } else {
                    _uiState.value = StatusUiState.Error("Erro na resposta: ${response.code()}")
                }
            } catch (e: IOException) {
                _uiState.value = StatusUiState.Error("Erro de rede: ${e.localizedMessage}")
            } catch (e: HttpException) {
                _uiState.value = StatusUiState.Error("Erro HTTP: ${e.message}")
            } catch (e: Exception) {
                _uiState.value = StatusUiState.Error("Erro desconhecido: ${e.localizedMessage}")
            }
        }
    }

    fun gerarPix(request: PixRequest) {
        _pixUiState.value = PixUiState.Loading

        viewModelScope.launch {
            try {
                val response = apiPagamento.gerarPix(request).execute()

                if (response.isSuccessful && response.body() != null) {
                    _pixUiState.value = PixUiState.Success(response.body()!!)
                } else {
                    _pixUiState.value = PixUiState.Error("Erro na resposta: ${response.code()}")
                }
            } catch (e: IOException) {
                _pixUiState.value = PixUiState.Error("Erro de rede: ${e.localizedMessage}")
            } catch (e: HttpException) {
                _pixUiState.value = PixUiState.Error("Erro HTTP: ${e.message}")
            } catch (e: Exception) {
                _pixUiState.value = PixUiState.Error("Erro desconhecido: ${e.localizedMessage}")
            }
        }
    }

    fun resetarEstado() {
        _uiState.value = StatusUiState.Idle
        _pixUiState.value = PixUiState.Idle
    }
}
