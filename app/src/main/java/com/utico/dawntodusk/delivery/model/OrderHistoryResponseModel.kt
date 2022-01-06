package com.utico.dawntodusk.delivery.model

data class OrderHistoryResponseModel(
    val DeliveryOrderHistory: List<DeliveryOrderHistory>,
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val OrderCount:Int
)
data class DeliveryOrderHistory(
    val deliveredDate: String,
    val deliveryStatus:String,
    val orderDate: String,
    val orderId: String,
    val orderStatus:String,
    val paymentPaid: String
)