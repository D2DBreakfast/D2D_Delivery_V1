package com.utico.dawntodusk.delivery.model

data class LoginResponseModel(
    val LoginData: LoginData,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)
data class LoginData(
    val _id: String,
    val deliveryBoyId: String,
    val email: String,
    val fullName: String,
    val idProof: String,
    val managerId: String,
    val mobileNo: String,
    val mobileOtp: String,
    val registerDate: String,
    val sectorId: String,
    val userType: String
)