package com.example.appautohub.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appautohub.data.model.pedidos.PedidoRequisicao
import com.example.appautohub.data.model.pedidos.PedidoUiModel
import com.example.appautohub.data.model.pedidos.PedidosResponseDto
import com.example.appautohub.data.model.pedidos.ProdutoUiModel
import com.example.appautohub.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PedidoViewModel : ViewModel() {

    private val _pedidoCriado = MutableLiveData<Result<PedidosResponseDto>>()
    val pedidoCriado: LiveData<Result<PedidosResponseDto>> get() = _pedidoCriado

    fun criarPedido(clienteId: Int, carrinho: List<Int>, intalacao: Boolean) {
        viewModelScope.launch {
            try {
                val requisicao = PedidoRequisicao(carrinho, intalacao)
                val response = RetrofitInstance.api.criarPedido(requisicao, clienteId)

                if (response.isSuccessful && response.body() != null) {
                    _pedidoCriado.postValue(Result.success(response.body()!!))
                } else {
                    _pedidoCriado.postValue(Result.failure(Exception("Erro ao criar pedido: ${response.code()}")))
                }
            } catch (e: Exception) {
                _pedidoCriado.postValue(Result.failure(e))
            }
        }
    }


    private val _pedidosUsuario = MutableStateFlow<List<PedidoUiModel>>(emptyList())
    val pedidosUsuario: StateFlow<List<PedidoUiModel>> = _pedidosUsuario

    fun buscarPedidosPorUsuario(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPedidos(id)
                if (response.isSuccessful && response.body() != null) {
                    val pedidos = response.body()!!.map { dto ->
                        val listaProdutos = dto.produtos.map { produtoDto ->
                            ProdutoUiModel(
                                nome = produtoDto.nome,
                                preco = produtoDto.preco
                            )
                        }
                        val valorTotal = listaProdutos.sumOf { it.preco }

                        PedidoUiModel(
                            tipo = "M. de obra",
                            data = "01/01/2025", // ou dto.dataPedido, se existir
                            produtos = listaProdutos,
                            valor = "R$ %.2f".format(valorTotal),
                            status = "Em andamento" // ou dto.status, se existir
                        )
                    }
                    _pedidosUsuario.value = pedidos
                }
            } catch (_: Exception) { }
        }
    }

}
