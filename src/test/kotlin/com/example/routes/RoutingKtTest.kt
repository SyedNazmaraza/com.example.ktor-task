package com.example.routes

import com.example.model.SchemeModel
import com.example.plugins.configRouting
import com.example.utils.BaseResponse
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RoutingKtTest{

    @Test
    fun `get route testing`(){
        return testApplication {
            application {
                configRouting()
            }
            val response = client.get("http://localhost:8080/scheme")
            val responseJson = Json.decodeFromString<BaseResponse.SuccessResponse<Array<SchemeModel>>>(response.bodyAsText())
            assertEquals(HttpStatusCode.OK,response.status)
        }

    }
}