package com.abernikov.multiplatformapp.android

import com.example.example.ExampleJson2KtKotlin
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovie(): Response<ExampleJson2KtKotlin> {
        return apiService.getMovie()
    }
}