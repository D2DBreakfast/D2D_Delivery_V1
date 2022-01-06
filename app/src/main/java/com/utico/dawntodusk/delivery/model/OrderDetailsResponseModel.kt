package com.utico.dawntodusk.delivery.model

data class OrderDetailsResponseModel(
    val DeliveryOrderDetails: List<DeliveryOrderDetail>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)
data class DeliveryOrderDetail(
    val deliveryStatus: String,
    val itemCount: Int,
    val landMark: String,
    val orderDate: String,
    val orderDetails: List<OrderDetail>,
    val orderId: String,
    val orderStatus: String,
    val paymentMethod: String,
    val paymentPaid: String,
    val paymentStatus: String,
    val sectorId: String,
    val userId: String,
    val userName: String,
    val villa: String
)
data class OrderDetail(
    val cartId: String,
    val itemBaseQuantity: String,
    val itemFoodType: Boolean,
    val itemId: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    val itemSubCategoryName: String
)