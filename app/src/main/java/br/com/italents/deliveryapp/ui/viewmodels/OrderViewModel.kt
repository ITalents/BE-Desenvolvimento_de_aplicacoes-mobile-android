package br.com.italents.deliveryapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.italents.deliveryapp.data.local.entity.OrderEntity
import br.com.italents.deliveryapp.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository) :
    ViewModel() {

    fun getAllOrders() = liveData {
        var result: List<OrderEntity> = listOf()

        result = orderRepository.getAllOrders()

        emit(result)
    }
}