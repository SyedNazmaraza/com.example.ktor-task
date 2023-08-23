package com.example.repository

import com.example.model.SchemeModel
import com.example.model.SchemeRequest
import com.example.utils.BaseResponse

interface SchemeRepository {

    suspend fun addScheme(model :SchemeModel):BaseResponse<String>
    suspend fun searchScheme(request: SchemeRequest):BaseResponse<List<SchemeModel>>
}