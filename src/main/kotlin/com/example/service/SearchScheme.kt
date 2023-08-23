package com.example.service

import com.example.model.SchemeModel
import com.example.model.SchemeRequest
import com.example.repository.SchemeRepository
import com.example.utils.BaseResponse

class SearchScheme(
    private val repo:SchemeRepository
) {
    suspend operator fun invoke(request:SchemeRequest):BaseResponse<List<SchemeModel>>{
        return repo.searchScheme(request)
    }
}