package com.example.service

import com.example.client.gettingData
import com.example.model.SchemeModel
import com.example.repository.SchemeRepository
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes

class GetAllSchemes(
    private val repo:SchemeRepository
) {
    suspend operator fun invoke(): BaseResponse<Array<SchemeModel>> {
        val result = gettingData()
        if (result is BaseResponse.SuccessResponse){
            result.response.forEach {
                repo.addScheme(it)
            }
        }
        return result
    }
}