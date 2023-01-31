package br.com.italents.deliveryapp.ui.interfaces

import br.com.italents.deliveryapp.data.models.Product

interface ProductCart {
    fun deleteProduct(product: Product)
    fun incrementProduct(product: Product)
    fun decrementProduct(product: Product)
}