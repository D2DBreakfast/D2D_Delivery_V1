package com.utico.dawntodusk.delivery.model

data class LoginResponseModel(
    val message: String,
    val otpData: OtpData,
    val status: Boolean,
    val statusCode: Int
)

data class OtpData(
    val _id: String,
    val adminId: String,
    val deliveryBoyId: String,
    val email: String,
    val fullName: String,
    val idProof: String,
    val mobileNo: String,
    val mobileNoOtp: String,
    val registerDate: String,
    val sector: String
)