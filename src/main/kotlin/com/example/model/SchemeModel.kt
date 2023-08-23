package com.example.model

import kotlinx.serialization.Serializable
//SCHEME_MODEL
@Serializable
data class SchemeModel(
    val schemeCode:String,
    val schemeName:String
)

@Serializable
data class SchemeRequest(
    val request:Request
)
@Serializable
data class Request(
    val scheme_name: String="",
    val schemeID : String="",
    val filter : String=""
)


@Serializable
data class SchemeWithIdModel(
    val meta : Meta,
    var data:List<Data>
)
@Serializable
data class Meta(
    val fund_house:String,
    val scheme_type:String,
    val scheme_category:String,
    val scheme_code:String,
    val scheme_name:String
)
@Serializable
data class Data(
    val date:String,
    val nav:String
)