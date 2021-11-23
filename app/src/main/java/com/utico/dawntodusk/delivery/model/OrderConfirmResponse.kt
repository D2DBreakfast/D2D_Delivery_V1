package com.utico.dawntodusk.delivery.model

data class OrderConfirmResponse(
    val message: String,
    val status: Boolean,
    val statusCode: Int
)