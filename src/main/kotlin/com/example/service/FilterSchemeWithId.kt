package com.example.service

import com.example.client.gettingDataByID
import com.example.model.Data
import com.example.model.SchemeRequest
import com.example.model.SchemeWithIdModel
import com.example.repository.SchemeRepository
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FilterSchemeWithId{
    suspend operator fun invoke(request:SchemeRequest):BaseResponse<SchemeWithIdModel> {
        val result = gettingDataByID(request.request.schemeID)
        if (result is BaseResponse.SuccessResponse) {
            try {
                val presentDate = LocalDate.now()
                val filter = request.request.filter
                val date = result.response.data
                val list = arrayListOf<Data>()
                val starts = if (filter.endsWith("M")) {
                    presentDate.minusMonths(filter.removeSuffix("M").toLong())
                } else if (filter.endsWith("W")) {
                    presentDate.minusWeeks(filter.removeSuffix("W").toLong())
                } else if (filter.endsWith("Y")) {
                    presentDate.minusYears(filter.removeSuffix("Y").toLong())
                } else {
                    return BaseResponse.ErrorResponse(ErrorCodes.BAD_REQUEST, ErrorCodes.BAD_REQUEST.msg)
                }
                date.forEach {
                    val d = LocalDate.parse(it.date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    if (d.isAfter(starts) && d.isBefore(presentDate) ) {
                        list.add(it)
                    }
                }
                result.response.data = list
                return result
            }
            catch (e:Exception){
                return BaseResponse.ErrorResponse(ErrorCodes.BAD_REQUEST,ErrorCodes.BAD_REQUEST.msg)
            }
        }
        return result
    }
}