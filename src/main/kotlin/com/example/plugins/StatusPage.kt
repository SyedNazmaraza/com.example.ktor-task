package com.example.plugins

import com.example.exception.DataBaseErrorException
import com.example.model.SchemeRequest
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


fun Application.configStatusPage(){
    install(StatusPages){
        exception<Throwable>(){call, cause ->
            when(cause){
                is RequestValidationException ->
                    call.respond(BaseResponse.ErrorResponse(ErrorCodes.BAD_REQUEST,cause.reasons.toString()))
                is DataBaseErrorException ->
                    call.respond(BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,cause.message.toString()))
            }
        }
    }
}

fun Application.configRequestValidation(){
    install(RequestValidation){
        validate<SchemeRequest> {
            if(it.request.scheme_name=="" && (it.request.schemeID=="" || it.request.filter=="")){
                ValidationResult.Invalid("Error in Given Fields")
            }
            else {
                ValidationResult.Valid
            }
        }
    }
}