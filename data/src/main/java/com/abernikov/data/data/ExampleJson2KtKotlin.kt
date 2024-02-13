package com.example.example

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin(

    @SerializedName("docs") var docs: List<Docs> = mutableListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("limit") var limit: Int? = null,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("pages") var pages: Int? = null

)