package com.utico.dawntodusk.delivery.model

data class UserItemOrderDetailsModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val userOrderDetails: UserOrderDetails
)
data class UserOrderDetails(
    val deliveryStatus: String,
    val deliveryAddress:String,
    val mobileNo:String,
    val orderDate: String,
    val orderId: String,
    val orderStatus: String,
    val userId: String,
    val userName: String,
    val userOrderItems: List<UserOrderItem>
)
data class UserOrderItem(
    val cartId: String,
    val itemFoodType: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    val itemQuantity: String,
    val itemSubCategoryName: String
)