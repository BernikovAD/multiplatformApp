package com.abernikov.multiplatformapp.android


import com.example.example.Docs
import com.example.example.ExampleJson2KtKotlin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("X-API-KEY: BRD4ZAX-3TJ45XQ-Q3SYHYW-6JZXA89") //TODO заглушка, вынести
    @GET("/v1.4/movie")
    suspend fun getMovie(): Response<ExampleJson2KtKotlin>
}