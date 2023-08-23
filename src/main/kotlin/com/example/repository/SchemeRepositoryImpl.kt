package com.example.repository

import com.example.db.dbQuery
import com.example.entity.SchemeTable
import com.example.exception.DataBaseErrorException
import com.example.model.SchemeModel
import com.example.model.SchemeRequest
import com.example.utils.BaseResponse
import com.example.utils.toSchema
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll

class SchemeRepositoryImpl : SchemeRepository{
    override suspend fun addScheme(model: SchemeModel): BaseResponse<String> {
        return try {
            dbQuery {
                SchemeTable.insertAndGetId {
                    it[schemeName] = model.schemeName
                    it[schemeCode] = model.schemeCode
                }
            }
            BaseResponse.SuccessResponse(response = "Success")
        }
        catch (e:Exception){
            throw DataBaseErrorException("Error in Inserting Data")
        }
    }

    override suspend fun searchScheme(request: SchemeRequest): BaseResponse<List<SchemeModel>> {
        return try {
            dbQuery {
                val list = arrayListOf<SchemeModel>()
                val query =  SchemeTable.selectAll()
                query.forEach {
                    if (it.toSchema().schemeName.contains(request.request.scheme_name)) {
                        list.add(it.toSchema())
                    }
                }
                BaseResponse.SuccessResponse(response = list)
            }
        }
        catch (e:Exception){
            throw DataBaseErrorException("Error in Fetching Data")
        }
    }

}