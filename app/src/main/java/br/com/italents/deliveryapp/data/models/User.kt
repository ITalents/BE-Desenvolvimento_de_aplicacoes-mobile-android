package br.com.italents.deliveryapp.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nome")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("senha")
    val password: String,
    @SerializedName("imagem")
    val image: String,
    @SerializedName("admin")
    var isAdmin: Boolean = false,
    @SerializedName("enderecos")
    val address: List<Address>,
)