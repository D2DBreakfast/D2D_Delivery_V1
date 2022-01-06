package com.utico.dawntodusk.delivery.model

data class DeliveryOrderListResponse(
    val DeliveryOrderList: List<DeliveryOrder>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)
data class DeliveryOrder(
    val amount: String,
    val landMark: String,
    val mobileNo: String,
    val orderDate: String,
    val orderId: String,
    val paymentMethod: String,
    val userName: String,
    val userId:String,
    val villaNo: String
)