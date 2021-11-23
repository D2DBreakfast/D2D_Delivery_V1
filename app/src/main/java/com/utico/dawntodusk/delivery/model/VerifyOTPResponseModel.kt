package com.utico.dawntodusk.delivery.model

data class VerifyOTPResponseModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int
)