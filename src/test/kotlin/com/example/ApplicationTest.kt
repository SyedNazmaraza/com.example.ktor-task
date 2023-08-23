package com.example

import com.example.plugins.configKoin
import com.example.plugins.configRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configKoin()
            configRouting()
        }
        client.post("/scheme/search").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
