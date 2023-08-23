package com.example.client

import com.example.model.SchemeModel
import com.example.model.SchemeWithIdModel
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*
import java.lang.Exception

suspend fun gettingData(): BaseResponse<Array<SchemeModel>> {
    val client = HttpClient {
        install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation){
            gson()
        }
    }
    return try {
        val response = client.get("https://api.mfapi.in/mf"){}
        val schemas = Gson().fromJson(response.bodyAsText(), Array<SchemeModel>::class.java)
        return schemas?.let {
            BaseResponse.SuccessResponse(response = it)
        }?:BaseResponse.ErrorResponse(errorCode = ErrorCodes.API_ERROR, msg = ErrorCodes.API_ERROR.name)
    }
    catch (e:Exception){
        BaseResponse.ErrorResponse(errorCode = ErrorCodes.API_ERROR, msg = ErrorCodes.API_ERROR.name)
    }
}
suspend fun gettingDataByID(id:String):BaseResponse<SchemeWithIdModel>{
    val client = HttpClient(){
        install(ContentNegotiation){
            gson()
        }
    }
    return try {
        val response = client.get("https://api.mfapi.in/mf/${id}")
        val body = Gson().fromJson(response.bodyAsText(),SchemeWithIdModel::class.java)
        return body?.let {
            BaseResponse.SuccessResponse(response = it)
        }?:BaseResponse.ErrorResponse(errorCode = ErrorCodes.API_ERROR, msg = ErrorCodes.API_ERROR.name)
    }
    catch (e:Exception){
        BaseResponse.ErrorResponse(errorCode = ErrorCodes.API_ERROR, msg = ErrorCodes.API_ERROR.name)    }
}
