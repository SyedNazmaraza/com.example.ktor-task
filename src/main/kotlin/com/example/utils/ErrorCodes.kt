package com.example.utils

enum class ErrorCodes(val msg:String){
    API_ERROR("Invalid Uri"),
    DATABASE_ERROR("Database Error"),
    BAD_REQUEST("Given Data is Not Valid")
}