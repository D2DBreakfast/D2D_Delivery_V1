package com.utico.dawntodusk.delivery.model

data class DeliveryPendingOrdersResponseModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val userOrderDetails: List<UserOrderDetail>
)

data class UserOrderDetail(
    val address: String,
    val email: String,
    val orderId: String,
    val phone: String,
    val userId: String,
    val userName: String
)