package com.example.appautohub.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appautohub.data.model.LoginResponse
import com.example.appautohub.data.remote.*
import com.example.appautohub.data.remote.RetrofitPagamento.apiPagamento
import com.example.appautohub.data.state.PixUiState
import com.example.appautohub.data.state.StatusUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException



class ViewModelPagamento : ViewModel() {



    var loginResponse by mutableStateOf<LoginResponse?>(null)
        private set

    val id = loginResponse?.id.toString()


    var ultimoTxId by mutableStateOf<String?>(null)
        private set

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
                val response = apiPagamento.consultarStatus(UsuarioIdRequest(id))

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
                val response = apiPagamento.gerarPix(request)

                if (response.isSuccessful) {
                    val pixResponse = response.body()

                    if (pixResponse != null) {
                        _pixUiState.value = PixUiState.Success(pixResponse)
                        ultimoTxId = pixResponse.txid // 👈 salva o txid para consulta futura
                    } else {
                        _pixUiState.value = PixUiState.Error("Erro: resposta vazia")
                    }
                } else {
                    _pixUiState.value = PixUiState.Error("Erro HTTP: ${response.code()}")
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
