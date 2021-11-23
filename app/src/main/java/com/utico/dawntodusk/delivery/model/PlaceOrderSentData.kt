package com.utico.dawntodusk.delivery.model

data class PlaceOrderSentData(
    val address: String,
    val placeOrderData: List<PlaceOrderData>,
    val sector: String,
    val totalAmount: String,
    val userId: String
)

data class PlaceOrderData(
    val cartId: String,
    val itemFoodType: String,
    val itemId: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    val itemQuantity: String,
    val itemSubCategoryName: String
)