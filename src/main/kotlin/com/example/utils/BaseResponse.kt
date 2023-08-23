package com.example.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseResponse<out T>{

    @Serializable
    data class SuccessResponse<out T>(
        val response : T
    ):BaseResponse<T>()
    @Serializable
    data class ErrorResponse(
        val errorCode : ErrorCodes,
        val msg : String
    ):BaseResponse<Nothing>()
}